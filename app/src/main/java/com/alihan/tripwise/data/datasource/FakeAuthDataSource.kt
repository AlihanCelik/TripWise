package com.alihan.tripwise.data.datasource

import com.alihan.tripwise.di.model.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthDataSource @Inject constructor() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Pending)
    val authState: StateFlow<AuthState> = _authState

    fun checkAuthStatus() {
        // App defaults to logged out so login screen opens first
        _authState.value = AuthState.LoggedOut
    }

    fun login(email: String, password: String): Result<Unit> {
        _authState.value = AuthState.LoggedIn
        return Result.success(Unit)
    }

    fun signUp(name: String, email: String, password: String): Result<Unit> {
        _authState.value = AuthState.LoggedIn
        return Result.success(Unit)
    }

    fun sendPasswordReset(email: String): Result<Unit> {
        return Result.success(Unit)
    }

    fun verifyOtp(code: String): Result<Unit> {
        _authState.value = AuthState.LoggedIn
        return Result.success(Unit)
    }
}
