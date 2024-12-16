package com.soopeach.data.datasource

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soopeach.data.ANNOUNCEMENTS
import com.soopeach.domain.model.AnnouncementItem
import kotlinx.coroutines.tasks.await

class AnnouncementDataSourceImpl : AnnouncementDataSource {

    override suspend fun getAnnouncementList(): List<AnnouncementItem> {
        val result = Firebase.firestore
            .collection(ANNOUNCEMENTS)
            .get()
            .await()

        return result.map {
            val title = it.data["title"] as String
            val content = it.data["content"] as String
            val id = it.id
            AnnouncementItem(title, content, id)
        }
    }
}
