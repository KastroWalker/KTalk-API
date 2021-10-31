package com.example.controllers

import com.example.entities.User
import com.example.services.UserService
import com.example.utils.InvalidProperty
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.SerializationException
import org.valiktor.ConstraintViolationException

fun Route.userRoute() {
    route("/users") {
        insertUser()
        protected()
    }
}

fun Route.insertUser() {
    post {
        try {
            val service = UserService()
            val user = call.receive<User>()

            val newUser = service.create(user)

            return@post call.respond(status = HttpStatusCode.Created, newUser)
        } catch (exception: SerializationException) {
            return@post call.respondText("You must provide a {username, email, password}", status = HttpStatusCode.PaymentRequired)
        } catch (exception: ConstraintViolationException) {
            return@post call.respondText(InvalidProperty(exception).getMessage(), status = HttpStatusCode.UnprocessableEntity)
        } catch (exception: Exception) {
            return@post call.respondText("Error creating user", status = HttpStatusCode.InternalServerError)
        }
    }
}

fun Route.protected() {
    get {
        return@get call.respondText("Rota protegida")
    }
}