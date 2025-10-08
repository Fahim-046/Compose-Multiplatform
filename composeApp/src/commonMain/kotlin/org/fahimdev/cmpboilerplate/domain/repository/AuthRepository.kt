package org.fahimdev.cmpboilerplate.domain.repository

import org.fahimdev.cmpboilerplate.domain.model.User

interface AuthRepository {
    suspend fun getCurrentUser(): User?

    suspend fun signInWithGoogle(): Result<User?>

    suspend fun signOut()

    suspend fun isUserSignedIn(): Boolean
}