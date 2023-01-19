package com.example.todo.domain.enum

enum class Status(
    val value: String
) {
    OPEN("생성"),
    INPROGRESS("진행 중"),
    DONE("완료")
}