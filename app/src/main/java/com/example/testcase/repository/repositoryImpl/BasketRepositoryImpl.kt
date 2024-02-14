package com.example.testcase.repository.repositoryImpl

import androidx.lifecycle.LiveData
import com.example.testcase.common.Resource
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.db.Dao
import com.example.testcase.presentation.basket.model.BasketUiMapper
import com.example.testcase.presentation.basket.model.BasketUiModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import com.example.testcase.repository.BasketRepository
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val dao: Dao,
    private val mapper: BasketUiMapper
): BasketRepository {
    override suspend fun insertProduct(isim: DatabaseModel): Resource<BasketUiModel> = try {
        dao.insertToDb(isim)
        val uiModel = mapper.mapToUiModel(isim)
        Resource.Success(uiModel)
    } catch (e: Exception) {
        Resource.Error(e)
    }

    override val readAllData: LiveData<List<DatabaseModel>> = dao.getAllDbValues()
}