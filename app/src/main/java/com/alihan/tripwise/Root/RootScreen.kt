package com.alihan.tripwise.Root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alihan.tripwise.Navigation.AppScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootScreen(
    navController: NavHostController,
    rootScreenViewModel: RootScreenViewModel = hiltViewModel(),
    selectedTabIndex: Int
) {
    val authState by rootScreenViewModel.authState.collectAsState()



    when (authState) {
        is AuthState.Pending -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AuthState.LoggedIn -> {
            MainTabView(
                navController = navController,
                selectedTabIndexNav = selectedTabIndex
            )
        }

        is AuthState.LoggedOut -> {
            LaunchedEffect(Unit) {
                navController.navigate(AppScreen.Login.route) {
                    popUpTo(AppScreen.Root.route) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun MainTabView(navController: NavHostController, selectedTabIndexNav: Int) {
    TODO("Not yet implemented")
}