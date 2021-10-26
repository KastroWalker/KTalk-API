package com.example

import io.ktor.application.*
import io.ktor.server.netty.EngineMain
import com.example.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureHTTP()
}
