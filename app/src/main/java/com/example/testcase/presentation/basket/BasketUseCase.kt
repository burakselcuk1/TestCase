package com.example.testcase.presentation.basket

import androidx.lifecycle.LiveData
import com.example.testcase.common.Resource
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.basket.model.BasketUiModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import com.example.testcase.repository.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val dbRepository: BasketRepository
) {
    fun insertToDb(product: DatabaseModel): Flow<Resource<BasketUiModel>> {
        return flow {
            val dbValue = dbRepository.insertProduct(product)
            emit(dbValue)
        }.flowOn(Dispatchers.IO)
    }

    val readAllData: LiveData<List<DatabaseModel>> = dbRepository.readAllData


}