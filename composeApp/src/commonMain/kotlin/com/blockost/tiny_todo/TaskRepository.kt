package com.blockost.tiny_todo

import com.blockost.tiny_todo.task.Task
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * // TODO 2025-11-11 Blockost Handle secret properly
 */
private const val API_KEY = "xxxx"

private const val HEADER_API_KEY = "apiKey"

private const val BASE_URL = "https://fwsfjysxpkioifqxdqzk.supabase.co/rest/v1"

class TaskRepository {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getTasksWithSubtasks(completed: Boolean): List<Task> {
        try {
            return getTasks(completed)
        } catch (exception: Exception) {
            println("An unexpected error happened: ${exception.message}")
            return emptyList()
        }
    }

    private suspend fun getTasks(completed: Boolean): List<Task> {
        val tasks: List<Task> = httpClient.get("$BASE_URL/tasks?select=*,subtasks(*)&is_completed=eq.$completed") {
            headers[HEADER_API_KEY] = API_KEY
        }.body()

        return tasks
    }

}
