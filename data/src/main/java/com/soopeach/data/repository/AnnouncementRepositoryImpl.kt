package com.soopeach.data.repository

import com.soopeach.data.datasource.AnnouncementDataSource
import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.repository.AnnouncementRepository

class AnnouncementRepositoryImpl(
    private val announcementDataSource: AnnouncementDataSource
) : AnnouncementRepository {
    override suspend fun getAnnouncementList(): List<AnnouncementItem> {
        return announcementDataSource.getAnnouncementList()
    }
}