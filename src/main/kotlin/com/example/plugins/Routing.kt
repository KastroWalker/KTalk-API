package com.example.plugins

import com.example.controllers.loginRouter
import com.example.controllers.userRoute
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Ktalk API version 1.0", status = HttpStatusCode.OK)
        }
        userRoute()
        loginRouter()
    }
}
