package com.blockost.tiny_todo

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.blockost.tiny_todo.task.Task
import com.blockost.tiny_todo.task.TaskViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val taskVMs = mutableListOf<TaskViewModel>()
    for (i in 0..100) {
        taskVMs.add(TaskViewModel(Task("$i", "Task $i", completed = i % 2 == 0)))
    }

    MaterialTheme {
        LazyColumn(
            modifier = Modifier
                .safeContentPadding()
                .border(1.dp, Color.Blue)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = taskVMs,
                key = { taskVM -> taskVM.uiState.value.id }
            ) { taskVM ->
                val task = taskVM.uiState.collectAsState().value
                println("rendering task ${task.id}")
                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Red)
                        .fillMaxWidth()
                        .clickable { println("clicked $taskVM") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.completed,
                        onCheckedChange = { value -> taskVM.updateCompleted(value) }
                    )
                    Text(
                        text = task.title,
                        textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
                    )
                }

            }
        }
    }
}
