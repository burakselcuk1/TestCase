package com.example.testcase.common

import com.example.testcase.data.local.DatabaseModel

object PriceCalculator {
    fun calculateTotalPrice(databaseModels: List<DatabaseModel>): Double {
        var totalPrice = 0.0

        for (item in databaseModels) {
            totalPrice += item.price.toDoubleOrNull() ?: 0.0
        }
        return totalPrice
    }
}
