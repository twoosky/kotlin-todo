package com.example.todo.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    NOT_FOUND_TODO(HttpStatus.NOT_FOUND, "Not found todo"),
    MISMATCH_TYPE_VALUE(HttpStatus.BAD_REQUEST, "Mismatch query parameter"),
    INVALID_QUERY_PARAM(HttpStatus.BAD_REQUEST, "Invalid query parameter"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),
    INTERNAL_SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error")
}