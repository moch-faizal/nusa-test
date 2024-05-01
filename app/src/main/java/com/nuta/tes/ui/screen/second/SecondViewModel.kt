package com.nuta.tes.ui.screen.second

import androidx.lifecycle.ViewModel
import com.nuta.tes.common.InputFilterRegex
import com.nuta.tes.extensions.toCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SecondViewModel @Inject constructor() : ViewModel() {
    private val _formField = MutableStateFlow(FormInput())
    val formField: StateFlow<FormInput> = _formField.asStateFlow()

    fun calculateDiscount(totalBeforeDiscount: Int) {
        var remainingTotal = totalBeforeDiscount
        var totalDiscount = 0
        val discounts =
            formField.value.discount?.split(",")?.map { it.trim().toInt() }?.toIntArray()

        if (discounts?.isNotEmpty() == true) {
            for (discount in discounts) {
                val discountAmount = remainingTotal * discount / 100
                totalDiscount += discountAmount
                remainingTotal -= discountAmount
            }
        }

        _formField.value = _formField.value.copy(
            totalDiscount = totalDiscount.toCurrency(),
            totalAfterDiscount = remainingTotal.toCurrency()
        )
    }

    fun setTotal(value: String) {
        _formField.value = _formField.value.copy(
            total = if (value.isEmpty() || value.matches(InputFilterRegex.DecimalInput)) value else return
        )
    }

    fun setDiscount(value: String) {
        _formField.value = _formField.value.copy(
            discount = value
        )
    }
}

data class FormInput(
    val total: String? = null,
    val discount: String? = null,
    val totalDiscount: String? = "0",
    val totalAfterDiscount: String? = "0"
)