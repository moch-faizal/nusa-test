package com.nuta.tes.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nuta.tes.extensions.toDp
import com.nuta.tes.extensions.toSp
import com.nuta.tes.ui.theme.PrimaryColor

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    modifierText: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "",
    textColor: Color = Color.White,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(12.toDp()),
        modifier = modifier,
        elevation = null,
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.padding(
                    vertical = 8.toDp(), horizontal = 20.toDp()
                )
            ) {
                CircularProgressIndicator(
                    color = textColor, strokeWidth = 4.toDp(),
                    modifier = Modifier.size(60.toDp()),
                )
            }
        } else {
            Text(
                text = text,
                fontSize = 28.toSp(),
                color = textColor,
                modifier = modifierText.padding(
                    vertical = 8.toDp(), horizontal = 20.toDp()
                ),
            )
        }
    }
}