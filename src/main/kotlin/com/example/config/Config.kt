package com.example.config

import io.github.cdimascio.dotenv.dotenv

val dotenv = dotenv()
val environment: String = dotenv["ENVIRONMENT"]

data class Config (
    val DB_HOST: String = dotenv["DB_HOST_$environment"],
    val DB_USER: String = dotenv["DB_USER_$environment"],
    val DB_PASS: String = dotenv["DB_PASS_$environment"],
    val DB_DATABASE: String = dotenv["DB_DATABASE_$environment"],
    val DB_PORT: String = dotenv["DB_PORT_$environment"],
)