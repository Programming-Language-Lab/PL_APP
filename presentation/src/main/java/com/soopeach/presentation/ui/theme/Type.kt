package com.soopeach.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.soopeach.presentation.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
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

val PretendardRegularFontFamily = FontFamily(Font(R.font.pretendard_regular))
val PretendardSemiBoldFontFamily = FontFamily(Font(R.font.pretendard_semibold))

// Todo: Color
sealed class PLTypography {
    data object Korean {

        val H0 = TextStyle(
            fontFamily = PretendardSemiBoldFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        )

        val H1 = TextStyle(
            fontFamily = PretendardSemiBoldFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        )

        val H2 = TextStyle(
            fontFamily = PretendardSemiBoldFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )

        val B1_semiBold = TextStyle(
            fontFamily = PretendardSemiBoldFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )

        val B2_semiBold = TextStyle(
            fontFamily = PretendardSemiBoldFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
        )

        val B3_semiBold = TextStyle(
            fontFamily = PretendardSemiBoldFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
        )

        val B1_regular = TextStyle(
            fontFamily = PretendardRegularFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )

        val B2_regular = TextStyle(
            fontFamily = PretendardRegularFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        )

        val B3_regular = TextStyle(
            fontFamily = PretendardRegularFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )
    }

}