package com.alihan.tripwise.data.repository

import com.alihan.tripwise.data.datasource.FakeAuthDataSource
import com.alihan.tripwise.di.model.AuthState
import com.alihan.tripwise.domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: FakeAuthDataSource
) : AuthRepository {
    override val authState: StateFlow<AuthState> = dataSource.authState

    override suspend fun checkAuthStatus() {
        dataSource.checkAuthStatus()
    }

    override suspend fun login(email: String, password: String): Result<Unit> {
        return dataSource.login(email, password)
    }

    override suspend fun signUp(name: String, email: String, password: String): Result<Unit> {
        return dataSource.signUp(name, email, password)
    }

    override suspend fun sendPasswordReset(email: String): Result<Unit> {
        return dataSource.sendPasswordReset(email)
    }

    override suspend fun verifyOtp(code: String): Result<Unit> {
        return dataSource.verifyOtp(code)
    }
}
