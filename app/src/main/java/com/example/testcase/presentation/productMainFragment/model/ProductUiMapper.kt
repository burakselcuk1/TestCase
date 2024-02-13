package com.example.testcase.presentation.productMainFragment.model

import com.example.testcase.data.response.ProductsApiModel


class ProductUiMapper {

    fun mapToProductUiModel(apiModel: ProductsApiModel): ProductUiModel {
        return ProductUiModel(
            products = apiModel.products
        )
    }
}