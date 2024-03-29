package com.example.testcase.repository

import com.example.testcase.common.Resource
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel

interface ProductRepository {
    suspend fun getProduct(page: Int, limit: Int): Resource<MutableList<ProductUiModel>>
    suspend fun getSearchProduct(name: String): Resource<MutableList<ProductUiModel>>
}