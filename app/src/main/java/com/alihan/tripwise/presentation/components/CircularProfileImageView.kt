package com.alihan.tripwise.presentation.components

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.alihan.tripwise.R


@Composable
fun CircularProfileImageView(
    profileImageUrl: String? = null,
    profileImageUri: Uri? = null,
    size: Size = Size.Medium,
    modifier: Modifier = Modifier
) {

    val placeholderPainter =  painterResource(id =R.drawable.user)

    val imageData: Any? = profileImageUri ?: profileImageUrl?.takeIf { it.isNotBlank() }
    Log.d("CircularProfileImg", "imageData=$imageData | url=$profileImageUrl | uri=$profileImageUri")

    Box(modifier = modifier.size(size.dimension)) {
        if (imageData != null) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageData)
                    .crossfade(true)
                    // URL değişince disk cache'den eski resim gelmesin
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    // Cache key olarak URL'in kendisini kullan (varsayılan davranış)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading,
                    is AsyncImagePainter.State.Error -> {
                        Image(
                            painter = placeholderPainter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .matchParentSize()
                                .clip(CircleShape)
                                .background(Color.LightGray, CircleShape)
                        )
                    }
                    else -> SubcomposeAsyncImageContent()
                }
            }
        } else {
            Image(
                painter = placeholderPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(CircleShape)
                    .background(Color.LightGray, CircleShape)
            )
        }

    }
}

sealed class Size(val dimension: Dp) {
    object Mini : Size(30.dp)
    object XSmall : Size(40.dp)
    object Small : Size(50.dp)
    object Medium : Size(60.dp)
    object Large : Size(90.dp)
    object XLarge : Size(120.dp)
    class Custom(dimension: Dp) : Size(dimension)
}

