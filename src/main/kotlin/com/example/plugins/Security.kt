package com.example.plugins

import io.ktor.auth.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.*
import com.example.config.Config

val CONFIG = Config()

fun Application.configureSecurity() {
    val jwtIssuer = CONFIG.JWT_ISSUER
    val myRealm = CONFIG.JWT_REALM

    fun buildJwtVerifier() = JWT.require(Algorithm.HMAC512(CONFIG.JWT_PRIVATE_KEY))
                    .withIssuer(jwtIssuer)
                    .build()

    fun validateCredential(jwtCredential: JWTCredential) =
        if (jwtCredential.payload.getClaim("username").toString() == "\"admin\"") {
            JWTPrincipal(jwtCredential.payload)
        } else {
            null
        }

    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            verifier (buildJwtVerifier())
            validate { jwtCredential -> validateCredential(jwtCredential) }
        }
    }
}