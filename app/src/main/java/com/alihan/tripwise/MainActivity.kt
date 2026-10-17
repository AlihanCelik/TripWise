package com.alihan.tripwise

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alihan.tripwise.Navigation.AppScreen
import com.alihan.tripwise.Root.RootScreen
import com.alihan.tripwise.presentation.ui.Onboard
import com.alihan.tripwise.presentation.ui.home.HomeScreen
import com.alihan.tripwise.presentation.ui.login.ForgotPassword
import com.alihan.tripwise.presentation.ui.login.LoginScreen
import com.alihan.tripwise.presentation.ui.login.SignUpScreen
import com.alihan.tripwise.ui.theme.TripWiseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TripWiseTheme {
                AppNavigation()
            }
        }
    }
}


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
            Onboard(navController)
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
            ForgotPassword(navController)
        }
    }
}


