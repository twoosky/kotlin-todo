package com.example.todo.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseTimeEntity(
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    val modifiedAt: LocalDateTime = LocalDateTime.now()
)