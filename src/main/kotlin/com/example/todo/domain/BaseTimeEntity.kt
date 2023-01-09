package com.example.todo.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PostPersist
import javax.persistence.PrePersist

@MappedSuperclass
open class BaseTimeEntity (
    @field:Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    @field:Column(name = "modified_at")
    var modified_at: LocalDateTime? = null
) {
    @PrePersist
    fun onCreate() {
        this.createdAt = LocalDateTime.now()
        this.modified_at = createdAt
    }

    @PostPersist
    fun onModify() {
        this.modified_at = LocalDateTime.now()
    }

}