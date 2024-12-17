package com.soopeach.data.datasource

import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.model.AnnouncementPreviewItem

interface AnnouncementDataSource {

    suspend fun getAnnouncementList(): List<AnnouncementPreviewItem>

    suspend fun getAnnouncementDetail(announcementId: String): AnnouncementItem

    suspend fun postAnnouncement(title: String, content: String): Result<Unit>
}