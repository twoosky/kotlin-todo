package com.example.todo.repository

import com.example.todo.domain.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface TodoRepository : JpaRepository<Todo, Long> {
    override fun findAll(pageable: Pageable): Page<Todo>
    fun findAllByCreatedAtBetween(pageable: Pageable, start: LocalDateTime, end: LocalDateTime): Page<Todo>
}