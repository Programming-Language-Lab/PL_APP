package com.soopeach.data.network

import com.google.firebase.firestore.QuerySnapshot
import com.soopeach.domain.model.MemberState
import kotlinx.coroutines.flow.Flow

interface StatusBoardClient {

    fun getMembersStateFlow(): Flow<QuerySnapshot>

    suspend fun setMemberState(id: String, name: String, status: String)

    suspend fun addMember(name: String)
}