package com.example.todo.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val reason: String
) {
    NOT_FOUND_TODO(HttpStatus.NOT_FOUND, "Not found todo")
}