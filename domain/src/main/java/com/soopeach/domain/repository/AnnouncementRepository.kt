package com.soopeach.domain.repository

import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.model.AnnouncementPreviewItem

interface AnnouncementRepository {

    suspend fun getAnnouncementList(): List<AnnouncementPreviewItem>

    suspend fun getAnnouncementDetail(announcementId: String): AnnouncementItem

    suspend fun postAnnouncement(title: String, content: String): Result<Unit>

}