package com.alihan.tripwise.domain.model

sealed interface AuthState {
    data object Pending : AuthState
    data object LoggedIn : AuthState
    data object LoggedOut : AuthState
}
