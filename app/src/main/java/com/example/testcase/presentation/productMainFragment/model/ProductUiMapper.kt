package com.example.testcase.presentation.productMainFragment.model

import com.example.testcase.data.response.ProductsApiModel


class ProductUiMapper {

    fun mapToProductUiModel(apiModel: List<ProductsApiModel>): List<ProductUiModel> {
        val productUiModelList = mutableListOf<ProductUiModel>()
        for (apiProduct in apiModel) {
            val productUiModel = ProductUiModel(
                id = apiProduct.id,
                name = apiProduct.name,
                image = apiProduct.image,
                price = apiProduct.price,
                description = apiProduct.description,
                model = apiProduct.model,
                brand = apiProduct.brand,
                createdAt = apiProduct.createdAt
            )
            productUiModelList.add(productUiModel)
        }
        return productUiModelList
    }
}