package com.example.entities

import com.example.utils.hashPassword
import kotlinx.serialization.Serializable
import java.util.*
import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class User(
    val id: String,
    val username: String,
    val email: String,
    var password: String,
) {
    constructor(username: String, email: String, password: String) : this (UUID.randomUUID().toString(), username, email, password) {
        validate(this) {
            validate(User::username).isNotEmpty().hasSize(1, 50)
            validate(User::email).isNotEmpty().isEmail().hasSize(3, 50)
            validate(User::password).isNotEmpty().hasSize(8, 50)
        }

        this.password = hashPassword(password)
    }
}