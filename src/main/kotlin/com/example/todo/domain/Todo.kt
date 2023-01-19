package com.example.todo.domain

import com.example.todo.domain.enum.Status
import javax.persistence.*

@Entity
@Table(name = "todo")
class Todo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    val title: String,

    val content: String,

    @Enumerated(EnumType.STRING)
    val status: Status
) : BaseTimeEntity()