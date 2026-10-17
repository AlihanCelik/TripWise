package com.alihan.tripwise.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alihan.tripwise.presentation.auth.forgotpassword.ForgotPasswordScreen
import com.alihan.tripwise.presentation.auth.login.LoginScreen
import com.alihan.tripwise.presentation.auth.otp.OtpScreen
import com.alihan.tripwise.presentation.auth.signup.SignUpScreen
import com.alihan.tripwise.presentation.home.HomeScreen
import com.alihan.tripwise.presentation.onboarding.OnboardScreen
import com.alihan.tripwise.presentation.root.RootScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.Root.route
    ) {
        composable(AppScreen.Root.route) {
            RootScreen(navController, selectedTabIndex = 0)
        }
        composable(AppScreen.Onboard.route) {
            OnboardScreen(navController)
        }
        composable(AppScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(AppScreen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(AppScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(AppScreen.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(AppScreen.Otp.route) {
            OtpScreen(navController)
        }
    }
}
