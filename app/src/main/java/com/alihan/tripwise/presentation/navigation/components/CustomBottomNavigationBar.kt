package com.alihan.tripwise.presentation.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alihan.tripwise.ui.theme.Blue

@Composable
fun CustomBottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onCenterPlusClick: () -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) MaterialTheme.colorScheme.surface else Color.White
    val selectedColor = Blue
    val unselectedColor = Color.Gray

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 20.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(32.dp),
                clip = false
            ),
        shape = RoundedCornerShape(32.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tab 0: Home
            BottomNavItem(
                icon = Icons.Outlined.Home,
                label = "Home",
                isSelected = selectedTab == 0,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = { onTabSelected(0) }
            )

            // Tab 1: Calendar
            BottomNavItem(
                icon = Icons.Outlined.DateRange,
                label = "Calendar",
                isSelected = selectedTab == 1,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = { onTabSelected(1) }
            )

            // Center Floating Plus (+) Button
            Box(
                modifier = Modifier
                    .offset(y = (-8).dp)
                    .size(54.dp)
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .background(color = Blue, shape = CircleShape)
                    .clickable {
                        onCenterPlusClick()
                        onTabSelected(2)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Destination",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Tab 3: Messages
            BottomNavItem(
                icon = Icons.Outlined.Email,
                label = "Messages",
                isSelected = selectedTab == 3,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = { onTabSelected(3) }
            )

            // Tab 4: Profile
            BottomNavItem(
                icon = Icons.Outlined.Person,
                label = "Profile",
                isSelected = selectedTab == 4,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = { onTabSelected(4) }
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    onClick: () -> Unit
) {
    val color = if (isSelected) selectedColor else unselectedColor

    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = color
        )
    }
}
