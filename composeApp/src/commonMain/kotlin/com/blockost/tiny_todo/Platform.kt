package com.blockost.tiny_todo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform