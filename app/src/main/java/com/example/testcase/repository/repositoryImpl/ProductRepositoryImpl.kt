package com.example.testcase.repository.repositoryImpl

import com.example.testcase.common.Resource
import com.example.testcase.domain.ProductService
import com.example.testcase.presentation.productMainFragment.model.ProductUiMapper
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import com.example.testcase.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val mapper: ProductUiMapper
) : ProductRepository {

    override suspend fun getProduct(): Resource<List<ProductUiModel>> = try {
        val response = productService.getProducts()
        val uiModel = mapper.mapToProductUiModel(response)
        Resource.Success(uiModel)
    }catch (e: Exception){
        Resource.Error(e)
    }
}