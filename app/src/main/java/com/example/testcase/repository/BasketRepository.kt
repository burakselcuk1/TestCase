package com.example.testcase.repository

import androidx.lifecycle.LiveData
import com.example.testcase.common.Resource
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.basket.model.BasketUiModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel

interface BasketRepository {
    suspend fun insertProduct(product: DatabaseModel): Resource<BasketUiModel>

    val readAllData: LiveData<List<DatabaseModel>>
}