package org.sonusid.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtConfig {
    private const val secret = "chai"
    private const val issuer = "sonusid.me"
    private const val audience = "sonusid"
    private const val validityInMs = 36_000_00 * 10

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    fun generateToken(username: String): String = JWT.create()
        .withIssuer(issuer)
        .withAudience(audience)
        .withClaim("username", username)
        .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
        .sign(algorithm)
}
