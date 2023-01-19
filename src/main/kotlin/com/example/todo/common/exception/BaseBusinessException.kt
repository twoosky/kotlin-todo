package com.example.todo.common.exception

open class BaseBusinessException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message)