package com.example.todo.service

import com.example.todo.common.exception.ErrorCode
import com.example.todo.common.exception.TodoNotFoundException
import com.example.todo.domain.Todo
import com.example.todo.domain.enum.Status
import com.example.todo.dto.TodoRequestDto
import com.example.todo.dto.TodoResponseDto
import com.example.todo.repository.TodoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    @Transactional
    fun generate(request: TodoRequestDto): TodoResponseDto {
        val todo = todoRepository.save(
            Todo(
                id = null,
                title = request.title,
                content = request.content,
                status = Status.OPEN
            )
        )
        return TodoResponseDto(todo)
    }

    @Transactional
    fun update(request: TodoRequestDto, todoId: Long): TodoResponseDto {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw TodoNotFoundException(ErrorCode.NOT_FOUND_TODO)
        todo.updateInfo(request.title, request.content)
        todoRepository.saveAndFlush(todo)
        return TodoResponseDto(todo)
    }

    @Transactional
    fun delete(todoId: Long) {
        todoRepository.deleteById(todoId)
    }

    @Transactional(readOnly = true)
    fun getDetail(todoId: Long): TodoResponseDto {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw TodoNotFoundException(ErrorCode.NOT_FOUND_TODO)
        return TodoResponseDto(todo)
    }

    @Transactional(readOnly = true)
    fun getPreview(pageable: Pageable): Page<TodoResponseDto> {
        return todoRepository.findAll(pageable)
            .map { TodoResponseDto(it) }
    }

    @Transactional(readOnly = true)
    fun searchByDate(page: Int, size: Int, date: String): Page<TodoResponseDto> {
        val searchDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val start = searchDate.atStartOfDay()
        val end = LocalDateTime.of(searchDate, LocalTime.of(23, 59, 59))
        val pageable = PageRequest.of(page, size)

        return todoRepository.findAllByCreatedAtBetween(pageable, start, end)
            .map { TodoResponseDto(it) }
    }

    @Transactional
    fun updateStatus(todoId: Long, status: Status) {
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw TodoNotFoundException(ErrorCode.NOT_FOUND_TODO)
        todo.updateStatus(status)
    }
}