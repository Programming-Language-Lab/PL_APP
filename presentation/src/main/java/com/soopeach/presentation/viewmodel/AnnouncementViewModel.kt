package com.soopeach.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.model.AnnouncementPreviewItem
import com.soopeach.domain.repository.AnnouncementRepository
import kotlinx.coroutines.launch

data class AnnouncementState(
    val announcementItems: List<AnnouncementPreviewItem> = emptyList(),
    val detailedAnnouncementItem: AnnouncementItem? = null
)

class AnnouncementViewModel(
    private val announcementRepository: AnnouncementRepository
) : ViewModel() {

    private var _state by mutableStateOf(AnnouncementState())
    val state get() = _state

    private val scope = viewModelScope

    fun getAnnouncementList() {
        scope.launch {
            val itemList = announcementRepository.getAnnouncementList()
            _state = _state.copy(announcementItems = itemList)
        }
    }

    fun getAnnouncementDetail(announcementId: String) {
        scope.launch {
            val item = announcementRepository.getAnnouncementDetail(announcementId)
            _state = _state.copy(detailedAnnouncementItem = item)
        }
    }
}