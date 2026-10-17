package com.alihan.tripwise

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alihan.tripwise.Navigation.AppScreen
import com.alihan.tripwise.Root.RootScreen
import com.alihan.tripwise.presentation.ui.Onboard
import com.alihan.tripwise.presentation.ui.home.HomeScreen
import com.alihan.tripwise.presentation.ui.login.LoginScreen
import com.alihan.tripwise.presentation.ui.login.SignUpScreen
import com.alihan.tripwise.ui.theme.TripWiseTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()

            /*
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "onboard",
                    modifier = Modifier.padding(innerPadding),
                    enterTransition = {
                        // Sayfa açılırken: sola kayar
                        slideInHorizontally(initialOffsetX = { -it })
                    },
                    exitTransition = {
                        // Sayfa kapanırken: sola çıkar
                        slideOutHorizontally(targetOffsetX = { -it })
                    },
                    popEnterTransition = {
                        // Geri dönünce: sağdan girer
                        slideInHorizontally(initialOffsetX = { it })
                    },
                    popExitTransition = {
                        // Geri dönünce: sağa çıkar
                        slideOutHorizontally(targetOffsetX = { it })
                    }
                ) {
                    composable("onboard") { Onboard(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("signup") { SignUpScreen(navController) }
                }
            }
             */
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val context = LocalContext.current
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
    }
}


