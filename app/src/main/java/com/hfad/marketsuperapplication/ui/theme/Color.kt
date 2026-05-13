package com.hfad.marketsuperapplication.ui.theme

import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val purple200 = Color(0xFFBB86FC)
val purple500 = Color(0xFF6200EE)
val purple700 = Color(0xFF3700B3)
val teal200 = Color(0xFF03DAC5)

val veryLightGrey = Color(0x60DCDCDC)
val lightGreen200 = Color(0x9932CD32)

//val CalendarContract.Colors.lightGreen: Color
//    @Composable
//    get() = lightGreen200
val ColorScheme.lightGreen:Color
    get() = lightGreen200

val ColorScheme.tttt : Color
@Composable
get() = if(!isSystemInDarkTheme()) Color.White else lightGreen


