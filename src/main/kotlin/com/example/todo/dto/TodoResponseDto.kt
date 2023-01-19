package com.example.todo.dto

import com.example.todo.domain.Todo
import com.example.todo.domain.enum.Status

data class TodoResponseDto (
     val id: Long,
     val title: String,
     val content: String,
     val status: Status
) {
     constructor(todo: Todo) : this(
          id = todo.id ?: 0,
          title = todo.title,
          content = todo.content,
          status = todo.status
     )
}
