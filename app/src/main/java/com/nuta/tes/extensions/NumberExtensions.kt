package com.nuta.tes.extensions

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Number.toCurrency(currency: String = "", locale: Locale = Locale("id")): String {
    val formatter = NumberFormat.getCurrencyInstance(locale) as DecimalFormat
    with(formatter) {
        decimalFormatSymbols = decimalFormatSymbols.apply {
            currencySymbol = currency
        }
        maximumFractionDigits = 0
    }
    return formatter.format(this)
}