package com.example.services

import com.example.entities.User
import com.example.repositories.UserRepository
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val username: String,
    val email: String,
)

class UserService {
    private val repository = UserRepository()

    fun create(user: User): UserResponse {
        try {
            val newUser = repository.create(user)

            return UserResponse(newUser.id, newUser.username, newUser.email)
        } catch (error: Exception) {
            throw Exception("Error creating user")
        }
    }
}
