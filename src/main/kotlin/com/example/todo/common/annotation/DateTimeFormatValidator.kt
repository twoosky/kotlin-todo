package com.example.todo.common.annotation

import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DateTimeFormatValidator : ConstraintValidator<ValidDateTimeFormat, String> {

    private var pattern: String? = null

    override fun initialize(constraintAnnotation: ValidDateTimeFormat?) {
        this.pattern = constraintAnnotation?.pattern
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        } catch (e: DateTimeException) {
            false
        }
    }
}