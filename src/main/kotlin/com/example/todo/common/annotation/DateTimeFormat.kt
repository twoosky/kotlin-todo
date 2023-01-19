package com.example.todo.common.annotation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [DateTimeFormatValidator::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class DateTimeFormat(
    val pattern: String = "yyyy-MM-dd",
    val message: String = "시간 형식이 유효하지 않습니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
