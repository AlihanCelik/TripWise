package com.alihan.tripwise.data.repository

import com.alihan.tripwise.di.model.AuthState
import com.alihan.tripwise.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Pending)
    override val authState: StateFlow<AuthState> = _authState.asStateFlow()

    override suspend fun checkAuthStatus() {
        _authState.value = AuthState.LoggedOut
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        _authState.value = AuthState.LoggedIn
        return Result.success(Unit)
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<Unit> {
        _authState.value = AuthState.LoggedIn
        return Result.success(Unit)
    }

    override suspend fun sendPasswordReset(email: String): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun verifyOtp(code: String): Result<Unit> {
        _authState.value = AuthState.LoggedIn
        return Result.success(Unit)
    }
}
