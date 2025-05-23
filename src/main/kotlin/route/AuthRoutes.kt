package org.sonusid.route


import org.sonusid.auth.JwtConfig
import org.sonusid.auth.PasswordHasher
import org.sonusid.models.*
import org.sonusid.storage.UserStorage
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes() {
    route("/auth") {
        post("/signup") {
            val req = call.receive<LoginRequest>()
            val hashed = PasswordHasher.hash(req.password)
            val user = User(req.username, hashed)

            if (UserStorage.addUser(user)) {
                call.respond(mapOf("message" to "User registered successfully"))
            } else {
                call.respond(mapOf("error" to "Username already exists"))
            }
        }

        post("/login") {
            val req = call.receive<LoginRequest>()
            val user = UserStorage.getUser(req.username)

            if (user != null && PasswordHasher.verify(req.password, user.hashedPassword)) {
                val token = JwtConfig.generateToken(user.username)
                call.respond(TokenResponse(token))
            } else {
                call.respond(mapOf("error" to "Invalid credentials"))
            }
        }

        authenticate("auth-jwt") {
            get("/me") {
                val principal = call.principal<JWTPrincipal>()!!
                val username = principal.payload.getClaim("username").asString()
                call.respond(mapOf("user" to username))
            }
        }
    }
}
