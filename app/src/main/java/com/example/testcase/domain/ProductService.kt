package com.example.testcase.domain

import com.example.testcase.common.apiEndPoints.TextToImageEndPoints.PRODUCT
import com.example.testcase.data.response.ProductsApiModel
import retrofit2.http.Body
import retrofit2.http.GET

interface ProductService {
    @GET(PRODUCT)
    suspend fun getProducts(): ProductsApiModel
}