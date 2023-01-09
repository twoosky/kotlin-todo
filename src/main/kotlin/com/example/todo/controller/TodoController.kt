package com.example.todo.controller

import com.example.todo.common.ApiResponseDto
import com.example.todo.dto.TodoRequestDto
import com.example.todo.dto.TodoResponseDto
import com.example.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class TodoController(
    private val todoService: TodoService
) {

    @PostMapping("/todos")
    fun generate(@Valid @RequestBody request: TodoRequestDto): ResponseEntity<TodoResponseDto> {
        val response = todoService.generate(request)
        return ApiResponseDto.ok(response)
    }
}