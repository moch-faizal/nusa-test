package com.nuta.tes.ui.screen.fourth

import android.util.Log
import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FourthViewModel @Inject constructor() : ViewModel() {
    private var _financeIn = mutableListOf(
        FinanceIn(1, "2021-09-29", 350000),
        FinanceIn(2, "2021-10-01", 200000),
        FinanceIn(3, "2021-10-03", 300000),
        FinanceIn(4, "2021-10-05", 150000),
    )
    val financeIn = _financeIn

    private var _financeOut = mutableListOf(
        FinanceOut(1, "2021-09-30", 250000),
        FinanceOut(2, "2021-10-02", 100000),
        FinanceOut(3, "2021-10-04", 150000),
        FinanceOut(4, "2021-10-06", 50000),
    )
    val financeOut = _financeOut

    init {
        val financeStatements = mutableListOf<FinanceStatement>()

        val financeMap = mutableMapOf<String, Pair<Int, Int>>()
        financeIn.forEach {
            financeMap[it.date] = (financeMap[it.date] ?: Pair(0, 0)).copy(first = it.amount)
        }
        financeOut.forEach {
            financeMap[it.date] = (financeMap[it.date] ?: Pair(0, 0)).copy(second = it.amount)
        }

        var balance = 0
        financeMap.toList().sortedBy { it.first }.forEach { (date, amounts) ->
            val (amountIn, amountOut) = amounts
            balance += amountIn - amountOut
            if (!isOctoberDate(date)) {
                financeStatements.add(FinanceStatement(date, amountIn, amountOut, balance))
            }
        }

        financeStatements.forEach { Log.e("Final Result: ", it.toString()) }
    }

    fun isOctoberDate(dateString: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(dateString) as Date
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH is zero-based

        return month == Calendar.OCTOBER
    }
}

@Keep
data class FinanceIn(
    val id: Int,
    val date: String,
    val amount: Int
)

@Keep
data class FinanceOut(
    val id: Int,
    val date: String,
    val amount: Int
)

@Keep
data class FinanceStatement(
    val date: String,
    val amount_in: Int,
    val amount_out: Int,
    val balance: Int
)