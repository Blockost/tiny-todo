package com.blockost.tiny_todo

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class Task {
    val id: String
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var completed by mutableStateOf(false)

    constructor(id: String, title: String, description: String? = null, completed: Boolean = false) {
        this.id = id
        this.title = title

        if (description != null) {
            this.description = description
        }
        this.completed = completed
    }

}
