package com.example.todo.common.exception

import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

class ErrorResponseDto(
    val errorCode: String,
    val message: String,
    val errorInfo: List<Error> = listOf()
){
    constructor(code: ErrorCode): this(
        errorCode = code.status.name,
        message = code.message
    )

    constructor(code: ErrorCode, e: ConstraintViolationException): this(
        errorCode = code.status.name,
        message = code.message,
        errorInfo = Error.getInfo(e)
    )

    constructor(code: ErrorCode, e: MethodArgumentNotValidException): this(
        errorCode = code.status.name,
        message = code.message,
        errorInfo = Error.getInfo(e)
    )

    constructor(code: ErrorCode, e: MethodArgumentTypeMismatchException): this(
        errorCode = code.status.name,
        message = code.message,
        errorInfo = Error.getInfo(e)
    )

    class Error(
        val field: String,
        val message: String,
        val value: Any
    ) {
        companion object {
            fun getInfo(e: ConstraintViolationException): List<Error> {
                return e.constraintViolations.map {
                    Error(
                        it.propertyPath.last().name,
                        it.message,
                        it.invalidValue
                    )
                }
            }

            fun getInfo(e: MethodArgumentNotValidException): List<Error> {
                return e.bindingResult.allErrors.map {
                    Error(
                        (it as FieldError).field,
                        it.defaultMessage ?: "",
                        it.rejectedValue ?: ""
                    )
                }
            }

            fun getInfo(e: MethodArgumentTypeMismatchException): List<Error> {
                return listOf(
                    Error(
                        e.name,
                        e.errorCode,
                        e.value ?: ""
                    )
                )
            }
        }
    }
}