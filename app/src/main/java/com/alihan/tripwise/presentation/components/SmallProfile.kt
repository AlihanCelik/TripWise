package com.alihan.tripwise.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SmallProfile(
    profileImageUrl: String? = null,
    profileImageUri: Uri? = null,
    size: Size = Size.XSmall,
    title: String = "Favoriler",
    onProfileClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .wrapContentSize()
            .background(Color.LightGray)
            .clickable { onProfileClick() }
            .padding(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProfileImageView(
                profileImageUrl = profileImageUrl,
                profileImageUri = profileImageUri,
                size = size
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
