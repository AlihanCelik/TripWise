package com.alihan.tripwise.presentation.ui

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val isDark = isSystemInDarkTheme()
    val boxColor = if (isDark) Color.Black else Color.White
    val systemUiController = rememberSystemUiController()
    val backgroundColor = boxColor



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
                    0 -> PageContent(
                        title = "Welcome to TripWise",
                        color = boxColor,
                        page="1",
                        description = "Plan your trips easily and quickly"
                    )

                    1 -> PageContent(
                        title = "Discover New Places",
                        color = boxColor,
                        page = "2",
                        description = "Get personalized recommendations"
                    )

                    2 -> PageContent(
                        title = "Let's Get Started!",
                        description = "Tap the button below to continue",
                        color = boxColor,
                        page = "3",
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

@Composable
fun PageContent(
    title: String,
    color: Color,
    description: String,
    page:String,
    onButtonClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(2f / 3f)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 80.dp,
                        bottomEnd = 80.dp
                    ),
                )
                .background(
                    color = color,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 80.dp,
                        bottomEnd = 80.dp
                    )
                )
                .padding(vertical = 90.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome to",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "TripWise",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(modifier = Modifier.height(40.dp))
                Icon(
                    painter = painterResource(id = R.drawable.trip),
                    contentDescription = null,
                    Modifier.size(150.dp),
                    tint = Green,
                )
            }
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(2.dp, Green)
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                Text(
                    text = "Let's Go", fontSize = (15.sp),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Center)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }


    }
}
