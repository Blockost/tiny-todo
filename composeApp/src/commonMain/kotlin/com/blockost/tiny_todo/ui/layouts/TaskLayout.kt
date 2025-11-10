package com.blockost.tiny_todo.ui.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.blockost.tiny_todo.task.Task
import com.blockost.tiny_todo.task.TaskListViewModel
import org.jetbrains.compose.resources.painterResource
import tiny_todo.composeapp.generated.resources.Res
import tiny_todo.composeapp.generated.resources.close_24px

@Composable
fun TaskLayout(task: Task, vm: TaskListViewModel, modifier: Modifier = Modifier) {
    var showSubtasks by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { println("clicked ${task.id}") },
            // TODO 2025-11-09 Blockost Add shadow to make each row more visible
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Toggle task completion
            Checkbox(
                checked = task.completed, onCheckedChange = { vm.updateCompleted(task.id, it) })
            // Task title
            Text(
                modifier = Modifier.weight(1f),
                text = task.title,
                textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
            )
            // Subtask count + toggle visibility
            SuggestionChip(
                onClick = { showSubtasks = !showSubtasks },
                label = { Text("${task.subtasks.count { it.completed }} / ${task.subtasks.count()}") }
            )
            // Delete task
            IconButton(
                onClick = { vm.removeTask(task.id) },
            ) {
                Icon(
                    painter = painterResource(Res.drawable.close_24px),
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

        AnimatedVisibility(showSubtasks) {
            for (subtask in task.subtasks) {
                println("rendering subtask ${task.id}.${subtask.id}")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Checkbox(
                        checked = subtask.completed,
                        onCheckedChange = { vm.updateSubtaskCompleted(task.id, subtask.id, it) })
                    Text(
                        modifier = Modifier.weight(1f),
                        text = subtask.title,
                        textDecoration = if (subtask.completed) TextDecoration.LineThrough else TextDecoration.None,
                    )
                }
            }
        }
    }
}
