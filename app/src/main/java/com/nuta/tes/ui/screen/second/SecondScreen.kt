package com.nuta.tes.ui.screen.second

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
fun SecondScreen(
    viewModel: SecondViewModel = hiltViewModel()
) {
    val formField by viewModel.formField.collectAsState()

    Column(modifier = Modifier.padding(horizontal = 40.toDp(), vertical = 28.toDp())) {
        Text(
            text = "Input Total Price: ",
            fontSize = 24.toSp(),
        )
        FormInputField(
            placeholderText = "Masukkan total value",
            visualTransformation = NumberFormatVisualTransformation(separator = "."),
            keyboardType = KeyboardType.Number,
            value = formField.total.orEmpty(),
            onValueChange = viewModel::setTotal,
        )
        Spacer(modifier = Modifier.height(20.toDp()))
        Text(
            text = "Discount: ",
            fontSize = 24.toSp(),
        )
        FormInputField(
            placeholderText = "Masukkan discount value ex: 10, 20",
            keyboardType = KeyboardType.Number,
            value = formField.discount.orEmpty(),
            onValueChange = viewModel::setDiscount,
        )
        Spacer(modifier = Modifier.height(20.toDp()))
        ButtonPrimary(text = "Run", onClick = {
            if (!formField.total.isNullOrEmpty()) viewModel.calculateDiscount(formField.total!!.toInt())
        })
        Spacer(modifier = Modifier.height(40.toDp()))
        Text(text = "Total Discount: ${formField.totalDiscount}")
        Text(text = "Price after Discount: ${formField.totalAfterDiscount}")
    }
}