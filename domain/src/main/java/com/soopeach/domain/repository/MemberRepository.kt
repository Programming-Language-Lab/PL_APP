package com.soopeach.domain.repository

import com.soopeach.domain.model.MemberState
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    fun getMembersStateFlow(): Flow<List<MemberState>>

    suspend fun setMemberState(id: String, name: String, status: String)

    suspend fun addMember(name: String)

    suspend fun deleteMember(id: String)
}