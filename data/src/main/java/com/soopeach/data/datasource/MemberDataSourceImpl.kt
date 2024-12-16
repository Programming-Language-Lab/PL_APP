package com.soopeach.data.datasource

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import com.soopeach.data.BuildConfig
import com.soopeach.data.MEMBERS
import com.soopeach.data.model.MemberStateResponse
import com.soopeach.data.model.WebHookMessage
import com.soopeach.data.network.Client
import com.soopeach.domain.model.MemberStatus
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow

class MemberDataSourceImpl(): MemberDataSource {
    private val client = Client.getInstance()

    override fun getMembersStateFlow(): Flow<QuerySnapshot> {
        return Firebase.firestore.collection(MEMBERS)
            .snapshots()
    }

    override suspend fun setMemberState(
        id: String,
        name: String,
        status: String,
        position: String
    ) {
        Firebase.firestore.collection(MEMBERS)
            .document(id)
            .set(MemberStateResponse(name, status, position))

        postWebHookMessage(WebHookMessage("*${name}* 은/는 *$status*"))
    }

    private suspend fun postWebHookMessage(message: WebHookMessage) {
        client
            .post(BuildConfig.webhookUrl) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(message)
            }
    }

    override suspend fun addMember(name: String) {
        // Todo: position
        Firebase.firestore.collection(MEMBERS)
            .add(MemberStateResponse(name, MemberStatus.HOME.text, "F.E"))
    }

    override suspend fun deleteMember(id: String) {
        Firebase.firestore.collection(MEMBERS)
            .document(id)
            .delete()
    }
}