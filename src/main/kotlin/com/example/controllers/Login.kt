package com.example.controllers

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import com.example.utils.generateToken
import kotlinx.serialization.Serializable


@Serializable
data class UserLogin (
    val username: String,
    val password: String,
)

// TODO("Criar validação para tamanho da senha e username")

fun Route.loginRouter() {
    route("/login") {
        post {
            val user = call.receive<UserLogin>()

            if (user.username != "admin" && user.password != "admin") {
                return@post call.respondText("Invalid credentials")
            }

            val token = generateToken(user)
            call.respondText(token)
        }
    }
}