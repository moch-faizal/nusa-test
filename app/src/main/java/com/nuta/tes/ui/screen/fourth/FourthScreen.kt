package com.nuta.tes.ui.screen.fourth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FourthScreen(viewModel: FourthViewModel = hiltViewModel()) {
    Scaffold(containerColor = Color.White) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Bisa diliat di logcat untuk hasilnya, untuk logic dalam view model")
        }
    }
}