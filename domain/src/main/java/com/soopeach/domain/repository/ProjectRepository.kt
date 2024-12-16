package com.soopeach.domain.repository

import com.soopeach.domain.model.ProjectItem

interface ProjectRepository {

    suspend fun getProjectList(): List<ProjectItem>
}