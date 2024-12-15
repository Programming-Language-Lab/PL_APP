package com.soopeach.domain.model

data class MemberState(
    val name: String,
    val id: String,
    val status: MemberStatus,
    val position: String
)