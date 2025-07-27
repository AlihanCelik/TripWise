package com.alihan.tripwise.presentation.ui.home

import android.view.Display.Mode
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alihan.tripwise.R
import com.alihan.tripwise.presentation.ui.component.BestDestination
import com.alihan.tripwise.ui.theme.Blue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) MaterialTheme.colorScheme.surface else Color.White
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "Kırıkkale/Merkez",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Menu"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                text = "Explore the",
                fontSize = 18.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 8.dp)
                    .align(Alignment.Start),
                textAlign = TextAlign.Center
            )

            Row (modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 8.dp)
                .align(Alignment.Start)){
                Text(
                    text = "Beautiful",
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(end = 4.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "world!",
                    fontSize = 25.sp,
                    color = Blue,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {  },
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColor),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.create),
                        contentDescription = "Create",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "New Destination",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row (modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = "Best Destination",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Start
                )
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "View all",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.End
                    )

                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                items(fakeDestinations) { destination ->
                    BestDestination(
                        imageUrl = "https://assets.micontenthub.com/traveloffers/travel-tips/the-blue-mosque-in-istanbul-turkey_uBl9m1ErD.jpg",
                        destinationName = destination.name,
                        rating = destination.rating,
                        address = destination.address
                    )
                }
            }



            }



        }


}

data class Destination(
    val name: String,
    val rating: Double,
    val address: String,
    val imageRes: Int )

val fakeDestinations = listOf(
    Destination("Paris", 4.8, "France", R.drawable.img_1),
    Destination("Tokyo", 4.7, "Japan", R.drawable.img_1),
    Destination("Istanbul", 4.5, "Turkey", R.drawable.img_1),
    Destination("New York", 4.6, "USA", R.drawable.img_1),
    Destination("Rome", 4.4, "Italy", R.drawable.img_1)
)