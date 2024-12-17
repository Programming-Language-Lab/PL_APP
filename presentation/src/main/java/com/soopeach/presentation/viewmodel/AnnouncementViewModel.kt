package com.soopeach.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soopeach.domain.EMPTY
import com.soopeach.domain.model.AnnouncementItem
import com.soopeach.domain.model.AnnouncementPreviewItem
import com.soopeach.domain.repository.AnnouncementRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

sealed class AnnouncementEvent {
    data object PostSuccess : AnnouncementEvent()
    data object PostFailure : AnnouncementEvent()
}


data class AnnouncementState(
    val announcementItems: List<AnnouncementPreviewItem> = emptyList(),
    val detailedAnnouncementItem: AnnouncementItem? = null,
    val titleInputText: String = String.EMPTY,
    val contentInputText: String = String.EMPTY,
    val isPosting : Boolean = false
)

class AnnouncementViewModel(
    private val announcementRepository: AnnouncementRepository
) : ViewModel() {

    private var _state by mutableStateOf(AnnouncementState())
    val state get() = _state

    private var _event = Channel<AnnouncementEvent>()
    val event = _event

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

    fun changeTitleInputText(text: String) {
        _state = _state.copy(titleInputText = text)
    }

    fun changeContentInputText(text: String) {
        _state = _state.copy(contentInputText = text)
    }

    fun postAnnouncement() {
        scope.launch {

            _state = _state.copy(isPosting = true)
            val result = announcementRepository.postAnnouncement(
                title = state.titleInputText,
                content = state.contentInputText
            )
            _state = _state.copy(isPosting = false)

            result.onSuccess {
                _event.send(AnnouncementEvent.PostSuccess)
                _state = _state.copy(titleInputText = String.EMPTY, contentInputText = String.EMPTY)
            }.onFailure {
                _event.send(AnnouncementEvent.PostFailure)
            }
        }
    }
}