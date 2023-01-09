package com.example.todo.dto

import javax.validation.constraints.NotBlank

data class TodoRequestDto(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String
)
