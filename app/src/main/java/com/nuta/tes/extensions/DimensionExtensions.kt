package com.nuta.tes.extensions

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

fun Int.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).roundToInt().dp
}

fun Int.toSp() = (this / Resources.getSystem().displayMetrics.scaledDensity).roundToInt().sp

@Composable
fun Int.scaledSp(): TextUnit {
    val value: Int = this
    return with(LocalDensity.current) {
        val textSize = value / this.fontScale.roundToInt()
        if (this.fontScale.roundToInt() >= 1) value.sp else textSize.sp
    }
}