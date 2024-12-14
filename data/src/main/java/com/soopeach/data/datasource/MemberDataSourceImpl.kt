package com.soopeach.data.datasource

import com.google.firebase.firestore.QuerySnapshot
import com.soopeach.data.network.StatusBoardClient
import kotlinx.coroutines.flow.Flow

class MemberDataSourceImpl(
    private val statusBoardClient: StatusBoardClient
): MemberDataSource {
    override fun getMembersStateFlow(): Flow<QuerySnapshot> {
        return statusBoardClient.getMembersStateFlow()
    }

    override suspend fun setMemberState(id: String, name: String, status: String) {
        statusBoardClient.setMemberState(id, name, status)
    }

    override suspend fun addMember(name: String) {
        statusBoardClient.addMember(name)
    }

    override suspend fun deleteMember(id: String) {
        statusBoardClient.deleteMember(id)
    }
}