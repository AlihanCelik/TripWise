package com.alihan.tripwise.presentation.home.alldestinations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alihan.tripwise.presentation.home.components.ProfileImagePicker

@Composable
fun SmallProfile(
) {
    Box(
        modifier = Modifier.clip(
            shape = RoundedCornerShape(30.dp)
        ).wrapContentSize().background(Color.LightGray).padding(3.dp)
    ){
        Row(
            modifier = Modifier.padding(horizontal = 5.dp,vertical = 5.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color.Blue),contentAlignment = Alignment.Center){
                ProfileImagePicker(
                    selectedImageUri = null,
                    onImageClick = { }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Favoriler",
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

        }
    }


}