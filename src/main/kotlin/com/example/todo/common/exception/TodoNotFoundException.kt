package com.example.todo.common.exception

class TodoNotFoundException(
    errorCode: ErrorCode
) : BaseBusinessException(errorCode)