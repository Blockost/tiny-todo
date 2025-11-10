package com.blockost.tiny_todo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.blockost.tiny_todo.ui.screens.TaskListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        TaskListScreen()
    }
}
