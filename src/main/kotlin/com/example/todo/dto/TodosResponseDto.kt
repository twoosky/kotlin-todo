package com.example.todo.dto

import com.example.todo.domain.Todo

data class TodosResponseDto(
    val todos: MutableList<TodoResponseDto>? = mutableListOf()
) {

}
