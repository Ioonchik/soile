package com.example.soile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp
    ),
    displayLarge = TextStyle(
        fontSize = 72.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    labelLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)