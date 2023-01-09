package com.example.todo.controller

import com.example.todo.common.ApiResponseDto
import com.example.todo.common.annotation.ValidDateTimeFormat
import com.example.todo.domain.enum.Status
import com.example.todo.dto.TodoRequestDto
import com.example.todo.dto.TodoResponseDto
import com.example.todo.dto.TodosResponseDto
import com.example.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class TodoController(
    private val todoService: TodoService
) {

    @PostMapping("/todos")
    fun generate(
        @Valid @RequestBody request: TodoRequestDto
    ): ResponseEntity<TodoResponseDto> {
        val response = todoService.generate(request)
        return ApiResponseDto.created(response)
    }

    @PatchMapping("/todos/{todoId}")
    fun update(
        @Valid @RequestBody request: TodoRequestDto,
        @PathVariable todoId: Long
    ) : ResponseEntity<TodoResponseDto> {
        val response = todoService.update(request, todoId)
        return ApiResponseDto.ok(response)
    }

    @DeleteMapping("/todos/{todoId}")
    fun delete(@PathVariable todoId: Long) : ResponseEntity<Unit> {
        todoService.delete(todoId)
        return ApiResponseDto.noContent()
    }

    @GetMapping("/todos/{todoId}")
    fun getDetail(@PathVariable todoId: Long) : ResponseEntity<TodoResponseDto> {
        val response = todoService.getDetail(todoId)
        return ApiResponseDto.ok(response)
    }

    @GetMapping("/todos")
    fun getPreview(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ) : ResponseEntity<TodosResponseDto> {
        val response = todoService.getPreview(page, size)
        return ApiResponseDto.ok(response)
    }

    @GetMapping("/todos")
    fun searchByDate(
        @ValidDateTimeFormat time: String
    ) : ResponseEntity<TodosResponseDto> {
        val response = todoService.searchByDate(time)
        return ApiResponseDto.ok(response)
    }

    @PatchMapping("/todos")
    fun updateStatus(@RequestParam status: Status) : ResponseEntity<Unit> {
        val response = todoService.updatestatus(status)
        return ApiResponseDto.noContent()
    }
}