package com.nuta.tes.routes

sealed class Routes(val name: String) {
    data object HomeScreen : Routes("home_screen")
    data object FirstScreen : Routes("device_screen")
}