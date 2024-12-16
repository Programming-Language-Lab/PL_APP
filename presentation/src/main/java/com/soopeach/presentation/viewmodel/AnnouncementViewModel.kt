package com.soopeach.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.repository.AnnouncementRepository
import kotlinx.coroutines.launch

data class AnnouncementState(
    val announcementItems: List<AnnouncementItem> = emptyList()
)

class AnnouncementViewModel(
    private val announcementRepository: AnnouncementRepository
) : ViewModel() {

    private var _state by mutableStateOf(AnnouncementState())
    val state get() = _state

    private val scope = viewModelScope

    init {
        scope.launch {
            val itemList = announcementRepository.getAnnouncementList()
            println("테스트 아이템 리스트 $itemList")
            _state = _state.copy(announcementItems = itemList)
        }
    }
}