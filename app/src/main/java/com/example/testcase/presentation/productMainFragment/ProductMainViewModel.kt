package com.example.testcase.presentation.productMainFragment

import androidx.lifecycle.viewModelScope
import com.base.core.BaseViewModel
import com.example.testcase.common.Resource
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductMainViewModel @Inject constructor(
    private val productUseCase: ProductMainUseCase
): BaseViewModel() {

    private val _products: MutableStateFlow<Resource<MutableList<ProductUiModel>>?> = MutableStateFlow(null)
    val products: MutableStateFlow<Resource<MutableList<ProductUiModel>>?> = _products

    fun getProducts(page: Int, limit: Int){
        viewModelScope.launch {
            productUseCase.getProducts(page, limit).collect { result ->
                _products.emit(result)
            }
        }
    }
}