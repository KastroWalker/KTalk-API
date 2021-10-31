package com.example

import com.example.entities.User
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.services.UserResponse
import io.ktor.server.testing.*
import org.junit.Test
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.*
import kotlinx.serialization.decodeFromString
import kotlin.test.assertContains
import kotlin.test.assertEquals

class UserTest {
    @ExperimentalSerializationApi
    @Test
    fun `Should create a valid user`() {
        val user = User(username = "test", email = "test@gmail.com", password = "password123")

        withTestApplication({
                configureRouting()
                configureSerialization()
        }) {
            with(handleRequest(HttpMethod.Post, "/users") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToJsonElement(user).toString())
            }) {
                val userResponse = Json.decodeFromString<UserResponse>(response.content.toString())
                assertContains(response.content.toString(), "id")
                assertEquals(userResponse.email, user.email)
                assertEquals(userResponse.username, user.username)
                assertEquals(HttpStatusCode.Created, response.status())
            }
        }
    }
}