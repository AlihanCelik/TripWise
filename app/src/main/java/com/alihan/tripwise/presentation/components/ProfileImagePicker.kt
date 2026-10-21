package com.alihan.tripwise.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp

@Composable
fun ProfileImagePicker(
    selectedImageUri: Uri? = null,
    profileImageUrl: String? = null,
    size: Size = Size.XLarge,
    onImageClick: () -> Unit = {}
) {
    val editBadgeSize = when (size) {
        Size.Mini, Size.XSmall -> 16.dp
        Size.Small -> 20.dp
        Size.Medium -> 24.dp
        Size.Large -> 32.dp
        Size.XLarge -> 38.dp
        is Size.Custom -> (size.dimension.value * 0.3f).dp
    }

    val editIconSize = editBadgeSize * 0.7f

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(size.dimension)
    ) {
        CircularProfileImageView(
            profileImageUrl = profileImageUrl,
            profileImageUri = selectedImageUri,
            size = size,
            modifier = Modifier.clickable { onImageClick() }
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(editBadgeSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable { onImageClick() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Edit",
                tint = Color.White,
                modifier = Modifier.size(editIconSize)
            )
        }
    }
}
