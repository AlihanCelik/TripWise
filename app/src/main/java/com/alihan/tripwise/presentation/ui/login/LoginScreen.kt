package com.alihan.tripwise.presentation.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alihan.tripwise.R
import com.alihan.tripwise.ui.theme.Green
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val isDark = isSystemInDarkTheme()
    val boxColor = if (isDark) Color.Black else Color.White
    val systemUiController = rememberSystemUiController()
    val backgroundColor = boxColor
    val coroutineScope = rememberCoroutineScope()

    var phoneNumber by rememberSaveable { mutableStateOf("")}



    SideEffect {
        systemUiController.setStatusBarColor(
            color = backgroundColor,
        )
    }

    Scaffold(

        modifier = Modifier
            .fillMaxSize(),

        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                ,

        ) {
            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> PageContent1(
                        onButtonClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1) // ✅ burada artık hata yok
                            }
                        },
                        color = boxColor,
                    )

                    1 -> PageContent2(
                        phoneNumber = phoneNumber,
                        onPhoneNumberChange = { phoneNumber = it },
                        onButtonClick = {
                            coroutineScope.launch { pagerState.animateScrollToPage(2) }
                        },
                        color = boxColor,
                    )

                    2 -> PageContent3(
                        color = boxColor,
                        phoneNumber=phoneNumber,
                        onButtonClick = { navController.navigate("home") }
                    )
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                activeColor = Color.Blue,
                inactiveColor = Color.Gray
            )
        }
    }
}







