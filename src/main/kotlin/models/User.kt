package org.sonusid.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val hashedPassword: String
)

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class TokenResponse(val token: String)
