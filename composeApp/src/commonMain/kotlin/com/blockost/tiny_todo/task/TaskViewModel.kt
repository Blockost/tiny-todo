package com.blockost.tiny_todo.task

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for a [Task].
 */
class TaskViewModel(task: Task) : ViewModel() {
    private val _uiState = MutableStateFlow(task)
    val uiState = _uiState.asStateFlow()

    fun updateCompleted(completed: Boolean) {
        _uiState.update { state -> state.copy(completed = completed) }
    }
}
