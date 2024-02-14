package com.example.testcase.domain

import com.example.testcase.common.apiEndPoints.TextToImageEndPoints.PRODUCT
import com.example.testcase.data.response.ProductsApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET(PRODUCT)
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): MutableList<ProductsApiModel>


    @GET("products")
    suspend fun getSearchProducts(
        @Query("name") name: String
    ): MutableList<ProductsApiModel>
}