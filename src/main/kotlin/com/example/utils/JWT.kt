package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.controllers.UserLogin
import com.example.config.Config
import java.util.*

val CONFIG = Config()

private val jwtAudience = CONFIG.JWT_AUDIENCE
private val jwtIssuer = CONFIG.JWT_ISSUER

fun generateToken(user: UserLogin): String {
    return JWT.create()
        .withAudience(jwtAudience)
        .withIssuer(jwtIssuer)
        .withClaim("username", user.username)
        .withExpiresAt(Date(System.currentTimeMillis() + 900000))
        .sign(Algorithm.HMAC512(CONFIG.JWT_PRIVATE_KEY))
}