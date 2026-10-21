package com.alihan.tripwise.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alihan.tripwise.R


val InterFont = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    labelSmall = TextStyle(
        fontFamily = InterFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)

private fun TextStyle.scale(scale: Float): TextStyle {
    return this.copy(
        fontSize = (this.fontSize.value * scale).sp,
        lineHeight = (this.lineHeight.value * scale).sp
    )
}

fun scaleTypography(base: Typography, scale: Float): Typography {
    return Typography(
        displayLarge = base.displayLarge.scale(scale),
        displayMedium = base.displayMedium.scale(scale),
        displaySmall = base.displaySmall.scale(scale),
        headlineLarge = base.headlineLarge.scale(scale),
        headlineMedium = base.headlineMedium.scale(scale),
        headlineSmall = base.headlineSmall.scale(scale),
        titleLarge = base.titleLarge.scale(scale),
        titleMedium = base.titleMedium.scale(scale),
        titleSmall = base.titleSmall.scale(scale),
        bodyLarge = base.bodyLarge.scale(scale),
        bodyMedium = base.bodyMedium.scale(scale),
        bodySmall = base.bodySmall.scale(scale),
        labelLarge = base.labelLarge.scale(scale),
        labelMedium = base.labelMedium.scale(scale),
        labelSmall = base.labelSmall.scale(scale)
    )
}