package com.example.database

import com.example.config.Config
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

val CONFIG = Config()

data class ConnectionString(
    val host: String,
    val user: String,
    val password: String,
    val database: String,
    val port: String,
) {
    override fun toString(): String {
        return "mongodb://${this.user}:${this.password}@${this.host}:${this.port}/admin"
    }
}

class Connection {
    fun startConnection(): MongoDatabase {
        val connectionString = ConnectionString(
            host = CONFIG.DB_HOST,
            user = CONFIG.DB_USER,
            password = CONFIG.DB_PASS,
            database = CONFIG.DB_DATABASE,
            port = CONFIG.DB_PORT
        ).toString()

        val client = KMongo.createClient(connectionString)
        return client.getDatabase(CONFIG.DB_DATABASE)
    }
}