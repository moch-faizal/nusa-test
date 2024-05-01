package com.nuta.tes.ui.screen.first

import androidx.lifecycle.ViewModel
import com.nuta.tes.common.InputFilterRegex
import com.nuta.tes.extensions.toCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class FirstViewModel @Inject constructor() : ViewModel() {
    private val _formField = MutableStateFlow(FormInput())
    val formField: StateFlow<FormInput> = _formField.asStateFlow()

    fun calculateNetSales(total: Double) {
        val discount = (1 + ((formField.value.tax?.toDouble() ?: 0.0) / 100))

        val netSales = ceil(total / discount)
        val taxAmount = ceil(total - netSales)

        _formField.value = _formField.value.copy(
            netSales = netSales.toCurrency(),
            taxAmount = taxAmount.toCurrency()
        )
    }

    fun setTotal(value: String) {
        _formField.value = _formField.value.copy(
            total = if (value.isEmpty() || value.matches(InputFilterRegex.DecimalInput)) value else return
        )
    }

    fun setTax(value: String) {
        _formField.value = _formField.value.copy(
            tax = if (value.isEmpty() || value.matches(InputFilterRegex.DecimalInput)) value else return
        )
    }
}

data class FormInput(
    val total: String? = null,
    val tax: String? = null,
    val netSales: String? = "0",
    val taxAmount: String? = "0"
)