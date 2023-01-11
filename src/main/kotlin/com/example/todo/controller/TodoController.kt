package com.example.todo.controller

import com.example.todo.common.response.ApiResponseDto
import com.example.todo.common.annotation.DateTimeFormat
import com.example.todo.common.response.PageResponseDto
import com.example.todo.domain.enum.Status
import com.example.todo.dto.TodoRequestDto
import com.example.todo.dto.TodoResponseDto
import com.example.todo.service.TodoService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
@Validated
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
    ): ResponseEntity<TodoResponseDto> {
        val response = todoService.update(request, todoId)
        return ApiResponseDto.ok(response)
    }

    @DeleteMapping("/todos/{todoId}")
    fun delete(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.delete(todoId)
        return ApiResponseDto.noContent()
    }

    @GetMapping("/todos/{todoId}")
    fun getDetail(@PathVariable todoId: Long): ResponseEntity<TodoResponseDto> {
        val response = todoService.getDetail(todoId)
        return ApiResponseDto.ok(response)
    }

    @GetMapping("/todos")
    fun getPreview(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<PageResponseDto<TodoResponseDto>> {
        val pageable = PageRequest.of(page, size)
        val response = todoService.getPreview(pageable)
        return ApiResponseDto.ok(PageResponseDto(response))
    }

    @GetMapping("/todos/search")
    fun searchByDate(
        @DateTimeFormat("yyyy-MM-dd") @RequestParam date: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<PageResponseDto<TodoResponseDto>> {
        val response = todoService.searchByDate(page, size, date)
        return ApiResponseDto.ok(PageResponseDto(response))
    }

    @PatchMapping("/todos/{todoId}/status")
    fun updateStatus(
        @PathVariable todoId: Long,
        @RequestParam type: Status
    ): ResponseEntity<Unit> {
        todoService.updateStatus(todoId, type)
        return ApiResponseDto.noContent()
    }
}