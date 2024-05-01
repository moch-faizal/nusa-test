package com.nuta.tes.ui.screen.third

import androidx.lifecycle.ViewModel
import com.nuta.tes.common.InputFilterRegex
import com.nuta.tes.extensions.toCurrency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ThirdViewModel @Inject constructor() : ViewModel() {
    private val _formField = MutableStateFlow(FormInput())
    val formField: StateFlow<FormInput> = _formField.asStateFlow()

    fun calculateRevenue(total: Double) {
        val params = formField.value
        val afterMarkup = total * (1 + (params.markup?.toDouble() ?: 0.0) / 100)
        val restaurant = total * (1 - (params.share?.toDouble() ?: 0.0) / 100)
        val driver = afterMarkup - restaurant

        _formField.value = _formField.value.copy(
            restaurant = restaurant.toCurrency(),
            driver = driver.toCurrency()
        )
    }

    fun setPrice(value: String) {
        _formField.value = _formField.value.copy(
            price = if (value.isEmpty() || value.matches(InputFilterRegex.DecimalInput)) value else return
        )
    }

    fun setMarkup(value: String) {
        _formField.value = _formField.value.copy(
            markup = if (value.isEmpty() || value.matches(InputFilterRegex.DecimalInput)) value else return
        )
    }

    fun setShare(value: String) {
        _formField.value = _formField.value.copy(
            share = if (value.isEmpty() || value.matches(InputFilterRegex.DecimalInput)) value else return
        )
    }
}

data class FormInput(
    val price: String? = null,
    val markup: String? = null,
    val share: String? = null,
    val restaurant: String? = null,
    val driver: String? = null
)