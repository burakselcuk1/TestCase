package com.example.testcase
import com.example.testcase.common.PriceCalculator.calculateTotalPrice
import com.example.testcase.data.local.DatabaseModel
import org.junit.Test
import org.junit.Assert.*

class CalculatorTest {

    @Test
    fun `calculateTotalPrice should return sum of prices in list`() {
        val databaseModels = listOf(
            DatabaseModel(id = "item1", name = "", image = "", price = "10.0", description = "", model = "", brand = "", createdAt = ""),
            DatabaseModel(id = "item2", name = "", image = "", price = "20.0", description = "", model = "", brand = "", createdAt = ""),
            DatabaseModel(id = "item3", name = "", image = "", price = "30.0", description = "", model = "", brand = "", createdAt = "")
        )

        val totalPrice = calculateTotalPrice(databaseModels)

        assertEquals(60.0, totalPrice, 0.001)
    }

    @Test
    fun `calculateTotalPrice should return 0 if list is empty`() {
        val databaseModels = emptyList<DatabaseModel>()

        val totalPrice = calculateTotalPrice(databaseModels)

        assertEquals(0.0, totalPrice, 0.001)
    }

    @Test
    fun `calculateTotalPrice should ignore invalid prices and return sum of valid prices`() {
        val databaseModels = listOf(
            DatabaseModel(id = "item1", name = "", image = "", price = "10.0", description = "", model = "", brand = "", createdAt = ""),
            DatabaseModel(id = "item2", name = "", image = "", price = "invalid", description = "", model = "", brand = "", createdAt = ""),
            DatabaseModel(id = "item3", name = "", image = "", price = "30.0", description = "", model = "", brand = "", createdAt = "")
        )

        val totalPrice = calculateTotalPrice(databaseModels)

        assertEquals(40.0, totalPrice, 0.001)
    }
}
