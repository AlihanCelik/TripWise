package com.alihan.tripwise.Navigation

sealed class AppScreen(val route: String) {
    data object Onboard : AppScreen("onboard")
    data object Login : AppScreen("login")
    data object SignUp : AppScreen("signup")
    data object Home : AppScreen("home")
    data object Root : AppScreen("root")

}