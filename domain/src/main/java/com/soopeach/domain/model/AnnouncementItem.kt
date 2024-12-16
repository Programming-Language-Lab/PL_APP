package com.soopeach.domain.model

import com.soopeach.domain.EMPTY

data class AnnouncementItem(
    val title: String = String.EMPTY,
    val content: String = String.EMPTY,
    val id: String = String.EMPTY,
)
