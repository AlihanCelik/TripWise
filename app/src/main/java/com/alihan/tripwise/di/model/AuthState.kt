package com.alihan.tripwise.di.model

sealed interface AuthState {
    data object Pending : AuthState
    data object LoggedIn : AuthState
    data object LoggedOut : AuthState
}
