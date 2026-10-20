package com.alihan.tripwise.presentation.home.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alihan.tripwise.R

@Composable
fun ProfileImagePicker(
    selectedImageUri: Uri?,
    onImageClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(140.dp)
    ) {
        Image(
            painter = if (selectedImageUri != null)
                rememberAsyncImagePainter(selectedImageUri)
            else
                painterResource(R.drawable.user),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.3f))
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onImageClick() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Edit",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}