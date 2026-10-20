package com.alihan.tripwise.domain.repository

import com.alihan.tripwise.di.model.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val authState: StateFlow<AuthState>
    suspend fun checkAuthStatus()
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun signUp(name: String, email: String, password: String): Result<Unit>
    suspend fun sendPasswordReset(email: String): Result<Unit>
    suspend fun verifyOtp(code: String): Result<Unit>
}
