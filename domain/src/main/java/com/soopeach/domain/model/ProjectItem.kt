package com.soopeach.domain.model

import com.soopeach.domain.EMPTY

data class ProjectItem(
    val name: String = String.EMPTY,
    val projectUrl: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
    val colorCode: Long = 0L
)
