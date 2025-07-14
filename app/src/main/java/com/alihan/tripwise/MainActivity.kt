package com.alihan.tripwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alihan.tripwise.presentation.ui.Onboard
import com.alihan.tripwise.presentation.ui.login.LoginScreen
import com.alihan.tripwise.presentation.ui.login.SignUpScreen
import com.alihan.tripwise.ui.theme.TripWiseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TripWiseTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "onboard",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("onboard") { Onboard(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("signup") { SignUpScreen(navController) }
                    }
                }
            }
        }
    }
}
