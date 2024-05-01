package com.nuta.tes.ui.screen.third

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.nuta.tes.extensions.toDp
import com.nuta.tes.extensions.toSp
import com.nuta.tes.ui.component.ButtonPrimary
import com.nuta.tes.ui.component.FormInputField
import com.nuta.tes.utils.NumberFormatVisualTransformation

@ExperimentalComposeUiApi
@Composable
fun ThirdScreen(viewModel: ThirdViewModel = hiltViewModel()) {
    val formField by viewModel.formField.collectAsState()

    Column(modifier = Modifier.padding(horizontal = 40.toDp(), vertical = 28.toDp())) {
        Text(
            text = "Input Total Price: ",
            fontSize = 24.toSp(),
        )
        FormInputField(
            placeholderText = "Masukkan price value",
            visualTransformation = NumberFormatVisualTransformation(separator = "."),
            keyboardType = KeyboardType.Number,
            value = formField.price.orEmpty(),
            onValueChange = viewModel::setPrice,
        )
        Spacer(modifier = Modifier.height(20.toDp()))
        Text(
            text = "Markup: ",
            fontSize = 24.toSp(),
        )
        FormInputField(
            placeholderText = "Masukkan markup value",
            keyboardType = KeyboardType.Number,
            value = formField.markup.orEmpty(),
            onValueChange = viewModel::setMarkup,
        )
        Spacer(modifier = Modifier.height(20.toDp()))
        Text(
            text = "Share: ",
            fontSize = 24.toSp(),
        )
        FormInputField(
            placeholderText = "Masukkan share value",
            keyboardType = KeyboardType.Number,
            value = formField.share.orEmpty(),
            onValueChange = viewModel::setShare,
        )
        Spacer(modifier = Modifier.height(20.toDp()))
        ButtonPrimary(text = "Run", onClick = {
            if (!formField.price.isNullOrEmpty()) viewModel.calculateRevenue(formField.price!!.toDouble())
        })
        Spacer(modifier = Modifier.height(40.toDp()))
        Text(text = "Restaurant : ${formField.restaurant}")
        Text(text = "Driver: ${formField.driver}")
    }
}