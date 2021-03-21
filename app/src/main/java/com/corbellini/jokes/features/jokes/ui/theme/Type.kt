package com.corbellini.jokes.features.jokes.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.corbellini.jokes.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily =  FontFamily(
            Font(R.font.rubik_mono_regular),
            Font(R.font.rubik_mono_regular, FontWeight.W500),
            Font(R.font.rubik_mono_regular, FontWeight.Bold)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)