package com.alihan.tripwise.presentation.root

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alihan.tripwise.di.model.AuthState
import com.alihan.tripwise.presentation.calendar.CalendarScreen
import com.alihan.tripwise.presentation.home.HomeScreen
import com.alihan.tripwise.presentation.messages.MessagesScreen
import com.alihan.tripwise.presentation.navigation.AppScreen
import com.alihan.tripwise.presentation.navigation.components.CustomBottomNavigationBar
import com.alihan.tripwise.presentation.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootScreen(
    navController: NavHostController,
    viewModel: RootViewModel = hiltViewModel(),
    selectedTabIndex: Int = 0
) {
    val authState by viewModel.authState.collectAsState()

    when (authState) {
        is AuthState.Pending -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AuthState.LoggedOut -> {
            MainTabView(
                navController = navController,
                selectedTabIndexNav = selectedTabIndex
            )
        }

        is AuthState.LoggedIn -> {
            LaunchedEffect(Unit) {
                navController.navigate(AppScreen.Login.route) {
                    popUpTo(AppScreen.Root.route) { inclusive = true }
                }
            }
        }
    }
}

@Composable
fun MainTabView(navController: NavHostController, selectedTabIndexNav: Int = 0) {
    var selectedTab by rememberSaveable { mutableIntStateOf(selectedTabIndexNav) }

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                onCenterPlusClick = {
                    navController.navigate(AppScreen.AllDestinations.route)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            when (selectedTab) {
                0 -> HomeScreen(navController = navController)
                1 -> CalendarScreen()
                2 -> HomeScreen(navController = navController) // Center '+' action
                3 -> MessagesScreen()
                4 -> ProfileScreen()
                else -> HomeScreen(navController = navController)
            }
        }
    }
}
