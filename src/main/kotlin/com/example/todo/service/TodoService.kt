package com.example.todo.service

import com.example.todo.domain.Todo
import com.example.todo.domain.enum.Status
import com.example.todo.dto.TodoRequestDto
import com.example.todo.dto.TodoResponseDto
import com.example.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

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
}