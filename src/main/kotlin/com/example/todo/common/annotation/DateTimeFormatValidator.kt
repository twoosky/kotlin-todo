package com.example.todo.common.annotation

import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DateTimeFormatValidator : ConstraintValidator<DateTimeFormat, String> {

    private var pattern: String? = null

    override fun initialize(constraintAnnotation: DateTimeFormat?) {
        this.pattern = constraintAnnotation?.pattern
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        } catch (e: DateTimeException) {
            false
        }
    }
}