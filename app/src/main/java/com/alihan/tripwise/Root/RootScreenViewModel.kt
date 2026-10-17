package com.alihan.tripwise.Root

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class AuthState {
    object Pending : AuthState()
    object LoggedIn : AuthState()
    object LoggedOut : AuthState()
}

@HiltViewModel
class RootScreenViewModel @Inject constructor(): ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Pending)
    val authState: StateFlow<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val dummyPassword = "123456"
        val dummyUsername = "alihan"

        if (dummyUsername == "alihan" && dummyPassword == "123456") {
            _authState.value = AuthState.LoggedIn
        } else {
            _authState.value = AuthState.LoggedOut
        }
    }
}