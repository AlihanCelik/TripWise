package com.alihan.tripwise.domain.usecase

import com.alihan.tripwise.di.model.AuthState
import com.alihan.tripwise.domain.repository.AuthRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    val authState: StateFlow<AuthState> = repository.authState

    suspend operator fun invoke() {
        repository.checkAuthStatus()
    }
}
