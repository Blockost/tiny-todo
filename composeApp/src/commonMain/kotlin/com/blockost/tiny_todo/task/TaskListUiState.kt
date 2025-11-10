package com.blockost.tiny_todo.task

data class TaskListUiState(val tasks: List<Task> = emptyList(), val isLoading: Boolean = false)
