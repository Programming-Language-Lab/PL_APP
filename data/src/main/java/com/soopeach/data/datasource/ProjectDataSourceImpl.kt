package com.soopeach.data.datasource

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soopeach.data.PROJECTS
import com.soopeach.domain.model.ProjectItem
import kotlinx.coroutines.tasks.await

class ProjectDataSourceImpl: ProjectDataSource {

    override suspend  fun getProjectList(): List<ProjectItem> {
        val result = Firebase.firestore
            .collection(PROJECTS)
            .get()
            .await()

        return result.map {
            it.toObject(ProjectItem::class.java)
        }


    }
}