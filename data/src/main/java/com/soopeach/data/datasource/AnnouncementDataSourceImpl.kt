package com.soopeach.data.datasource

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soopeach.data.ANNOUNCEMENTS
import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.model.AnnouncementPreviewItem
import kotlinx.coroutines.tasks.await

class AnnouncementDataSourceImpl : AnnouncementDataSource {

    override suspend fun getAnnouncementList(): List<AnnouncementPreviewItem> {
        val result = Firebase.firestore
            .collection(ANNOUNCEMENTS)
            .get()
            .await()

        return result.map {
            val title = it.data["title"] as String
            val id = it.id
            AnnouncementPreviewItem(title, id)
        }
    }

    override suspend fun getAnnouncementDetail(announcementId: String): AnnouncementItem {
        val result = Firebase.firestore
            .collection(ANNOUNCEMENTS)
            .document(announcementId)
            .get()
            .await()

        val title = result.data?.get("title") as String
        val content = result.data?.get("content") as String
        val id = result.id

        return AnnouncementItem(title, content, id)
    }
}
