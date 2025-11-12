package com.blockost.tiny_todo.subtask

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subtask(
    val id: String,
    var title: String,
    @SerialName("is_completed")
    var completed: Boolean = false
)
