package com.example.todo.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponseDto {
    companion object Package {
        fun <T> ok(data: T): ResponseEntity<T> {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(data)
        }

        fun <T> created(data: T): ResponseEntity<T> {
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(data)
        }

        fun noContent(): ResponseEntity<Unit> {
            return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
        }
    }
}