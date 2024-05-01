package com.nuta.tes.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.nuta.tes.extensions.toDp
import com.nuta.tes.extensions.toSp
import com.nuta.tes.ui.theme.PrimaryColor
import com.nuta.test.R

@Composable
fun ToolbarComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.toDp(), horizontal = 40.toDp()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Icon Logo",
            modifier = Modifier.size(120.toDp())
        )
        Spacer(modifier = Modifier.width(12.toDp()))
        Text(
            text = "Nuta pos",
            color = PrimaryColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 48.toSp()
        )
    }
}