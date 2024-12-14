package com.soopeach.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soopeach.domain.EMPTY
import com.soopeach.domain.model.MemberState
import com.soopeach.domain.model.MemberStatus
import com.soopeach.domain.repository.MemberRepository
import kotlinx.coroutines.launch

data class AttendanceScreenState(
    val memberState: List<MemberState> = emptyList(),
    val selectedMember: MemberState = MemberState("", "", MemberStatus.INIT),
    val isBottomSheetVisible: Boolean = false,
    val isModalVisible: Boolean = false,
    val addingMemberText: String = String.EMPTY
)

class AttendanceScreenViewModel(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private var _state by mutableStateOf(AttendanceScreenState())
    val state get() = _state

    private val scope = viewModelScope

    init {
        scope.launch {
            memberRepository.getMembersStateFlow().collect {
                _state = state.copy(memberState = it)
            }
        }
    }

    fun changeBottomSheetVisibility(isVisible: Boolean) {
        _state = state.copy(isBottomSheetVisible = isVisible)
    }

    fun changeMemberState(newStatus: String) {
        scope.launch {

            val newMemberState = state.selectedMember.copy(status = MemberStatus.toEnum(newStatus))

            _state = state.copy(
                selectedMember = newMemberState,
                memberState = state.memberState.map {
                    if (it.id == newMemberState.id) {
                        newMemberState
                    } else {
                        it
                    }
                }
            )

            memberRepository.setMemberState(
                state.selectedMember.id,
                state.selectedMember.name,
                state.selectedMember.status.text
            )
        }
    }

    fun setSelectedMemberState(memberState: MemberState) {
        _state = state.copy(selectedMember = memberState)
    }

    fun changeModalVisibility(isVisible: Boolean) {
        _state = state.copy(isModalVisible = isVisible)
    }

    fun changeAddingMemberText(text: String) {
        _state = state.copy(addingMemberText = text)
    }

    fun addMember() {
        scope.launch {
            memberRepository.addMember(state.addingMemberText)
            _state = state.copy(addingMemberText = String.EMPTY)
        }
    }

}