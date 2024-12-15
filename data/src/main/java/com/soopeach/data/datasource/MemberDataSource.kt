package com.soopeach.data.datasource

import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow

interface MemberDataSource {

    fun getMembersStateFlow(): Flow<QuerySnapshot>

    suspend fun setMemberState(id: String, name: String, status: String, position: String)

    suspend fun addMember(name: String)

    suspend fun deleteMember(id: String)
}