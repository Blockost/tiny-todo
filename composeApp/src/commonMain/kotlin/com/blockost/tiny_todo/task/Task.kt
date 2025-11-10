package com.blockost.tiny_todo.task

import com.blockost.tiny_todo.subtask.Subtask

data class Task(
    val id: String,
    var title: String,
    var description: String? = null,
    var completed: Boolean = false,
    val subtasks: List<Subtask> = listOf()
)
