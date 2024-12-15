package com.soopeach.data.repository

import com.soopeach.data.datasource.MemberDataSource
import com.soopeach.domain.model.MemberState
import com.soopeach.domain.model.MemberStatus
import com.soopeach.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemberRepositoryImpl(
    private val memberDataSource: MemberDataSource
) : MemberRepository {
    override fun getMembersStateFlow(): Flow<List<MemberState>> {
        return memberDataSource.getMembersStateFlow().map {
            val tempList = mutableListOf<MemberState>()
            it.documents.forEach { document ->
                val name = document.data?.get("name") as String
                val state = document.data?.get("status") as String
                val id = document.id
                val position = document.data?.get("position") as String
                tempList.add(MemberState(name, id, MemberStatus.toEnum(state), position))
            }
            tempList
        }
    }

    override suspend fun setMemberState(id: String, name: String, status: String, position: String) {
        memberDataSource.setMemberState(id, name, status, position)
    }

    override suspend fun addMember(name: String) {
        memberDataSource.addMember(name)
    }

    override suspend fun deleteMember(id: String) {
        memberDataSource.deleteMember(id)
    }

}