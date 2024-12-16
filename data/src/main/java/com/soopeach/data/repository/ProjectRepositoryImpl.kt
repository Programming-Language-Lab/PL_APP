package com.soopeach.data.repository

import com.soopeach.data.datasource.ProjectDataSource
import com.soopeach.domain.model.ProjectItem
import com.soopeach.domain.repository.ProjectRepository

class ProjectRepositoryImpl(
    private val projectDataSource: ProjectDataSource
): ProjectRepository{
    override suspend fun getProjectList(): List<ProjectItem> {
        return projectDataSource.getProjectList()
    }
}