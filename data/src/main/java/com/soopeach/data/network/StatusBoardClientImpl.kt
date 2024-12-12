package com.soopeach.data.network

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import com.soopeach.data.BuildConfig
import com.soopeach.data.model.MemberStateResponse
import com.soopeach.data.model.WebHookMessage
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow

class StatusBoardClientImpl : StatusBoardClient {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }


    override fun getMembersStateFlow(): Flow<QuerySnapshot> {
        return Firebase.firestore.collection(DATA_BASE_NAME)
            .snapshots()
    }

    override suspend fun setMemberState(id: String, name: String, status: String) {
        Firebase.firestore.collection(DATA_BASE_NAME)
            .document(id)
            .set(MemberStateResponse(name, status))

        postWebHookMessage(WebHookMessage("*${name}* 은/는 *$status*"))
    }

    private suspend fun postWebHookMessage(message: WebHookMessage) {
        client
            .post(BuildConfig.webhookUrl) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(message)
            }
    }

    companion object {
        private const val FIRE_STORE_END_POINT = "https://firestore.googleapis.com/v1"
        private const val PROJECT_ID = "pllab-97a85"
        private const val DATA_BASE_NAME = "Members"
        private const val FIRE_STORE_BASE_URL =
            "$FIRE_STORE_END_POINT/projects/$PROJECT_ID/databases/(default)/documents/$DATA_BASE_NAME"
    }
}