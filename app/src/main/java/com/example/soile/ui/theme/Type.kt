package com.example.soile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.soile.R

val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold)
)

private val base = TextStyle(fontFamily = Nunito)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = base.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = base.copy(
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp
    ),
    displayLarge = base.copy(
        fontSize = 72.sp,
        fontWeight = FontWeight.Bold,
    ),
    titleLarge = base.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    bodyMedium = base.copy(
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    labelLarge = base.copy(
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

