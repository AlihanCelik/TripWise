package com.alihan.tripwise.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alihan.tripwise.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Konumunuz",
                            color = MaterialTheme.colorScheme.onBackground,
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
                .fillMaxWidth()
                .padding(innerPadding)) {

        }

    }
}