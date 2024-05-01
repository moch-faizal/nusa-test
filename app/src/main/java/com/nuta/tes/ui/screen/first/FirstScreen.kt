package com.nuta.tes.ui.screen.first

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
fun FirstScreen(
    viewModel: FirstViewModel = hiltViewModel()
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
            text = "Pajak: ",
            fontSize = 24.toSp(),
        )
        FormInputField(
            placeholderText = "Masukkan pajak value",
            keyboardType = KeyboardType.Number,
            value = formField.tax.orEmpty(),
            onValueChange = viewModel::setTax,
        )
        Spacer(modifier = Modifier.height(20.toDp()))
        ButtonPrimary(text = "Run", onClick = {
            if (!formField.total.isNullOrEmpty()) viewModel.calculateNetSales(formField.total!!.toDouble())
        })
        Spacer(modifier = Modifier.height(40.toDp()))
        Text(
            text = "Net Sales: ${formField.netSales}"
        )
        Text(
            text = "Pajak: ${formField.taxAmount}"
        )
    }
}