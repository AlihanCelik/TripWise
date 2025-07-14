package com.alihan.tripwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alihan.tripwise.R

// Set of Material typography styles to start with
val SpartanBold = FontFamily(Font(R.font.spartanbold))
val SpartanExtraBold = FontFamily(Font(R.font.spartanextrabold))
val SpartanMedium = FontFamily(Font(R.font.spartanmedium))
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SpartanMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SpartanExtraBold,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SpartanBold,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )
)