package com.example.utils

import org.valiktor.ConstraintViolationException

data class InvalidProperty(
    val exception: ConstraintViolationException
) {
    fun getMessage(): String {
        val property = this.exception.constraintViolations.first().property
        val value = this.exception.constraintViolations.first().value
        return "'$value' is an invalid $property"
    }
}