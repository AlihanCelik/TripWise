package com.alihan.tripwise.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alihan.tripwise.R
import com.alihan.tripwise.presentation.components.SmallProfile
import com.alihan.tripwise.presentation.home.components.HomeMapCard
import com.alihan.tripwise.presentation.home.components.NewDestinationCard
import com.alihan.tripwise.presentation.home.components.ProfileHomeCard
import com.alihan.tripwise.presentation.navigation.AppScreen
import com.alihan.tripwise.presentation.ui.component.BestDestinationCard
import com.alihan.tripwise.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val destinations by viewModel.destinations.collectAsState()
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) MaterialTheme.colorScheme.surface else Color.White

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                actions = {
                    HomeMapCard()
                },
                title = {},
                navigationIcon = {
                    ProfileHomeCard()
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.padding(horizontal = 3.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                text = "Explore the",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 8.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 8.dp)
                    .align(Alignment.Start)
            ) {
                Text(
                    text = "Beautiful",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(end = 4.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "world!",
                    fontWeight = FontWeight.Bold,
                    color = Blue,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            NewDestinationCard {}
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Best Destination",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start
                )
                TextButton(onClick = { navController.navigate(AppScreen.AllDestinations.route) }) {
                    Text(
                        text = "View all",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Blue,
                        textAlign = TextAlign.End
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                items(destinations) { destination ->
                    BestDestinationCard(
                        imageUrl = destination.imageUrl,
                        destinationName = destination.name,
                        rating = destination.rating,
                        address = destination.address
                    )
                }
            }
        }
    }
}


