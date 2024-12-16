package com.soopeach.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soopeach.domain.model.ProjectItem
import com.soopeach.domain.repository.ProjectRepository
import kotlinx.coroutines.launch

data class HallOfFameScreenState(
    val projectItems: List<ProjectItem> = emptyList()
)

class HallOfFameScreenViewModel(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private var _state by mutableStateOf(HallOfFameScreenState())
    val state get() = _state

    private val scope = viewModelScope

    init {

        scope.launch {
            val projectItems = projectRepository.getProjectList()
            _state = state.copy(
                projectItems = projectItems
            )
        }

    }
}