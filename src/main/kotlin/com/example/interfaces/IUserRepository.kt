package com.example.interfaces

import com.example.entities.User

interface IUserRepository {
    fun create(user: User): User
}