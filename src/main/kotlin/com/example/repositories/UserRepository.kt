package com.example.repositories

import com.example.entities.User
import com.example.interfaces.IUserRepository
import com.example.database.Connection
import org.litote.kmongo.*

class UserRepository: IUserRepository {
    private val database = Connection().startConnection()
    private val collection = database.getCollection<User>()

    override fun create(user: User): User {
        try {
            this.collection.insertOne(user)

            if (this.collection.findOne("{id: '${user.id}'}") != null) {
                return user
            } else {
                throw Exception()
            }
        } catch (exception: Exception) {
            throw Exception("Error creating user")
        }
    }
}