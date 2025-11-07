package com.blockost.tiny_todo.task

data class Task(val id: String, var title: String, var description: String? = null, var completed: Boolean = false)
