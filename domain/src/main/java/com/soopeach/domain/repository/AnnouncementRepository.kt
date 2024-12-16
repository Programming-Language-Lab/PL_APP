package com.soopeach.domain.repository

import com.soopeach.domain.model.AnnouncementItem

interface AnnouncementRepository {

    suspend fun getAnnouncementList(): List<AnnouncementItem>

}