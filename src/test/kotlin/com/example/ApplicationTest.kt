package com.example

import com.example.plugins.configureRouting
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals


class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({
                configureRouting()
        }) {
            with(handleRequest(HttpMethod.Get, "/") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }) {
                assertEquals(response.status(), HttpStatusCode.OK)
                assertEquals(response.content, "Ktalk API version 1.0")
            }
        }
    }
}