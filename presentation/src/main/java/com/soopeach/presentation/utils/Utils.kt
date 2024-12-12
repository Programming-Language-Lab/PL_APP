package com.soopeach.presentation.utils

import androidx.compose.ui.graphics.Color
import com.soopeach.domain.model.MemberStatus

fun String.getStatusColor(): Color {
    return when (this) {
        MemberStatus.IN.text -> Color(0xFF10B981)
        MemberStatus.OUT.text -> Color(0xFFF59E0B)
        MemberStatus.CLASS.text -> Color(0xFF8B5CF6)
        else -> Color(0xFF3B82F6)
    }
}