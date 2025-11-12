package com.blockost.tiny_todo.task

import com.blockost.tiny_todo.subtask.Subtask
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    var title: String,
    var description: String? = null,
    @SerialName("is_completed")
    var completed: Boolean = false,
    val subtasks: List<Subtask> = listOf()
)
