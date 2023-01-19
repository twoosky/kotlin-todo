package com.example.todo.common.response

import org.springframework.data.domain.Page

data class PageResponseDto<T>(
    val content: List<T>,
    val totalCount: Long,
    val totalPage: Int,
    val currentCount: Int,
    val currentPage: Int
) {
    constructor(page: Page<T>) : this(
        content = page.content,
        totalCount = page.totalElements,
        totalPage = page.totalPages,
        currentCount = page.numberOfElements,
        currentPage = page.number
    )
}