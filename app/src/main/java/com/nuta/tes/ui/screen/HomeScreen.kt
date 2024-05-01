package com.nuta.tes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.nuta.tes.extensions.toDp
import com.nuta.tes.ui.component.ToolbarComponent
import com.nuta.tes.ui.screen.first.FirstScreen
import com.nuta.tes.ui.screen.fourth.FourthScreen
import com.nuta.tes.ui.screen.second.SecondScreen
import com.nuta.tes.ui.screen.third.ThirdScreen
import com.nuta.tes.ui.theme.AccentColor
import com.nuta.tes.ui.theme.Line
import com.nuta.tes.ui.theme.PrimaryColor

@ExperimentalComposeUiApi
@ExperimentalLayoutApi
@Composable
fun HomeScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("First", "Second", "Third", "Fourth")

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(modifier = Modifier.padding(it)) {
            Surface(
                color = Color.White,
                shadowElevation = 8.toDp(), // play with the elevation values
            ) {
                Column {
                    ToolbarComponent()
                    TabRow(
                        containerColor = Color.White,
                        selectedTabIndex = selectedTabIndex,
                        divider = {
                            HorizontalDivider(thickness = 2.toDp(), color = Line)
                        },
                        indicator = { tabPositions ->
                            if (selectedTabIndex < tabPositions.size) {
                                TabRowDefaults.Indicator(
                                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                    color = AccentColor
                                )
                            }
                        }
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(text = {
                                Text(
                                    title,
                                    fontWeight = if (selectedTabIndex == index) FontWeight.SemiBold else FontWeight.Light,
                                    color = PrimaryColor
                                )
                            },
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index }
                            )
                        }
                    }
                }
            }
            when (selectedTabIndex) {
                0 -> FirstScreen()
                1 -> SecondScreen()
                2 -> ThirdScreen()
                3 -> FourthScreen()
            }
        }
    }
}