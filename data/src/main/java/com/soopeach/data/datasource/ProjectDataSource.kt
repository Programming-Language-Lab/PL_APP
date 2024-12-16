package com.soopeach.data.datasource

import com.soopeach.domain.model.ProjectItem

interface ProjectDataSource {
    suspend fun getProjectList(): List<ProjectItem>
}