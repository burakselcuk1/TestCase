package com.example.testcase.presentation.productMainFragment

import com.example.testcase.common.Resource
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import com.example.testcase.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductMainUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun getProducts(page: Int, limit: Int): Flow<Resource<MutableList<ProductUiModel>>> =
        flow {
            val resource = repository.getProduct(page,limit)
            if (resource is Resource.Success) {
                emit(Resource.Success(resource.data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.throwable))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getSearchProducts(name: String): Flow<Resource<MutableList<ProductUiModel>>> =
        flow {
            val resource = repository.getSearchProduct(name)
            if (resource is Resource.Success) {
                emit(Resource.Success(resource.data))
            } else if (resource is Resource.Error) {
                emit(Resource.Error(resource.throwable))
            }
        }.flowOn(Dispatchers.IO)

}