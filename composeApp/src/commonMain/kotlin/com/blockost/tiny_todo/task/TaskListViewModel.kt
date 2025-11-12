package com.blockost.tiny_todo.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blockost.tiny_todo.TaskRepository
import com.blockost.tiny_todo.subtask.Subtask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskListViewModel(val useMockTasks: Boolean = false) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskListUiState())

    val uiState = _uiState.asStateFlow()

    private val taskRepository = TaskRepository()

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        _uiState.update { it.copy(isLoading = true) }
        val tasks = mutableListOf<Task>()

        // TODO 2025-11-11 Blockost Make sure network request should be initiated by the view model itself
        viewModelScope.launch {
            if (useMockTasks) {
                // Simulate data fetching from a repository
                kotlinx.coroutines.delay(1000)

                for (i in 0..100) {
                    tasks.add(
                        Task(
                            id = "$i",
                            title = "Task $i",
                            completed = i % 2 == 0,
                            subtasks = listOf(Subtask("$i", "Subtask $i"))
                        )
                    )
                }
            } else {
                val fetchedTasks = taskRepository.getTasksWithSubtasks(false)
                tasks.addAll(fetchedTasks)
            }

            _uiState.update { it.copy(tasks = tasks, isLoading = false) }
        }
    }

    fun updateCompleted(taskId: String, completed: Boolean) {
        _uiState.update { currentState ->
            val updatedTasks = currentState.tasks.map { task: Task ->
                if (task.id == taskId) {
                    task.copy(completed = completed)
                } else {
                    task
                }
            }

            currentState.copy(tasks = updatedTasks)
        }
    }

    fun removeTask(taskId: String) {
        _uiState.update { currentState ->
            val updatedTasks = currentState.tasks.filter { it.id != taskId }
            currentState.copy(tasks = updatedTasks)
        }
    }

    fun updateSubtaskCompleted(taskId: String, subtaskId: String, completed: Boolean) {
        _uiState.update { currentState ->
            val updatedTasks = currentState.tasks.map { task: Task ->
                if (task.id == taskId) {
                    val updatedSubtasks = task.subtasks.map { subtask ->
                        if (subtask.id == subtaskId) {
                            subtask.copy(completed = completed)
                        } else {
                            subtask
                        }
                    }
                    task.copy(subtasks = updatedSubtasks)
                } else {
                    task
                }
            }

            currentState.copy(tasks = updatedTasks)
        }
    }
}
