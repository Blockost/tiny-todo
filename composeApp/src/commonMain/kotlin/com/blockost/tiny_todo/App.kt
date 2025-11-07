package com.blockost.tiny_todo

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.blockost.tiny_todo.task.Task
import com.blockost.tiny_todo.task.TaskViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tiny_todo.composeapp.generated.resources.Res
import tiny_todo.composeapp.generated.resources.close_24px

@Composable
@Preview
fun App() {
    val taskVMs = remember { mutableStateListOf<TaskViewModel>() }
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
                        .padding(8.dp)
                        .clickable { println("clicked $taskVM") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.completed,
                        onCheckedChange = { value -> taskVM.updateCompleted(value) }
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = task.title,
                        textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
                    )
                    IconButton(
                        onClick = { taskVMs.remove(taskVM) }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.close_24px),
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }

            }
        }
    }
}
