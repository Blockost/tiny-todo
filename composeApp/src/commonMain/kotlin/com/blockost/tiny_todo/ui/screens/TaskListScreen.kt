package com.blockost.tiny_todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blockost.tiny_todo.task.TaskListViewModel
import com.blockost.tiny_todo.ui.layouts.TaskLayout


@Composable
fun TaskListScreen(vm: TaskListViewModel = viewModel()) {
    val uiState by vm.uiState.collectAsState()

    if (uiState.isLoading) {
        println("Loading...")
        Column(
            modifier = Modifier
                .safeContentPadding()
                .border(1.dp, Color.Blue)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(modifier = Modifier.size(100.dp))
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .safeContentPadding()
                .border(1.dp, Color.Blue)
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = uiState.tasks,
                key = { task -> task.id }
            ) { task ->
                println("rendering task ${task.id}")
                TaskLayout(task, vm, modifier = Modifier.animateItem())
            }
        }
    }
}
