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

    override suspend fun getProduct(page: Int, limit: Int): Resource<MutableList<ProductUiModel>> = try {
        val response = productService.getProducts(page, limit)
        val uiModel = mapper.mapToProductUiModel(response)
        Resource.Success(uiModel)
    }catch (e: Exception){
        Resource.Error(e)
    }

    override suspend fun getSearchProduct(name: String): Resource<MutableList<ProductUiModel>>  = try {
        val response = productService.getSearchProducts(name)
        val uiModel = mapper.mapToProductUiModel(response)
        Resource.Success(uiModel)
    }catch (e: Exception){
        Resource.Error(e)
    }
}