package com.example.testcase.presentation.basket.model

import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel

class BasketUiMapper {
    fun mapToUiModel(dbModel: DatabaseModel): BasketUiModel {
        return BasketUiModel(
            id = dbModel.id,
            model = dbModel.model,
            brand = dbModel.brand,
            image = dbModel.image,
            createdAt = dbModel.createdAt,
            price = dbModel.price,
            description = dbModel.description,
            name = dbModel.name
        )
    }
}