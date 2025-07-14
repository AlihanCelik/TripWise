package com.alihan.tripwise.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alihan.tripwise.R
import com.alihan.tripwise.ui.theme.Blue
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboard(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()




    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
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
                        title = "Life is short and the world is wide",
                        image = R.drawable.img,
                        buttonText = "Get Started",
                        description = "At Friends tours and travel, we customize reliable and trutworthy educational tours to destinations all over the world",
                        onButtonClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )

                    1 -> PageContent(
                        title = "It’s a big world out there go explore",
                        image = R.drawable.img2,
                        buttonText = "Next",
                        description = "To get the best of your adventure you just need to leave and go where you like. we are waiting for you"
                        ,onButtonClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        }
                    )

                    2 ->
                        PageContent(
                            title = "People don’t take trips, trips take people",
                            image = R.drawable.img3,
                            buttonText = "Next",
                            description = "To get the best of your adventure you just need to leave and go where you like. we are waiting for you"
                            ,
                        onButtonClick = { navController.navigate("login") }
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
    title:String="",
    description:String="",
    image: Int ,
    buttonText:String="",
    onButtonClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxWidth()
                .fillMaxHeight(2f / 3f),

            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp).padding(horizontal = 24.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        Text(
            text = description,
            fontSize = 12.sp,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                onButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
            ),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(
                    text = buttonText, fontSize = (13.sp),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }




    }
}







