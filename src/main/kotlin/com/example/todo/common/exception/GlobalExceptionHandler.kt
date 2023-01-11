package com.example.todo.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(ErrorCode.MISMATCH_TYPE_VALUE, e))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(ErrorCode.INVALID_REQUEST, e))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(ErrorCode.INVALID_QUERY_PARAM, e))
    }

    @ExceptionHandler(BaseBusinessException::class)
    fun baseBusinessException(e: BaseBusinessException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(e.errorCode.status)
            .body(ErrorResponseDto(e.errorCode))
    }

    @ExceptionHandler(Exception::class)
    fun baseBusinessException(e: Exception): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponseDto(ErrorCode.INTERNAL_SEVER_ERROR))
    }
}