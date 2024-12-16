package com.soopeach.data.datasource

import com.soopeach.domain.model.AnnouncementItem

interface AnnouncementDataSource {

    suspend fun getAnnouncementList(): List<AnnouncementItem>

}