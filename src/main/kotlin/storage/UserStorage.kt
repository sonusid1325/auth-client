package org.sonusid.storage

import org.sonusid.modals.User

object UserStorage {
    val users = mutableMapOf<String, User>()

    fun addUser(user: User): Boolean {
        if (users.containsKey(user.username)) return false
        users[user.username] = user
        return true
    }

    fun getUser(username: String): User? = users[username]
}