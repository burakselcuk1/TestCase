package com.example.testcase.presentation.productDetail

import androidx.lifecycle.viewModelScope
import com.base.core.BaseViewModel
import com.example.testcase.common.Resource
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.basket.BasketUseCase
import com.example.testcase.presentation.basket.model.BasketUiModel
import com.example.testcase.presentation.productMainFragment.ProductMainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDbUseCase: BasketUseCase
): BaseViewModel() {

    private val _insertResult = MutableStateFlow<Resource<BasketUiModel>>(Resource.Loading)
    val insertResult: MutableStateFlow<Resource<BasketUiModel>> = _insertResult

    fun insertToDb(product: DatabaseModel) {
        viewModelScope.launch {
            productDbUseCase.insertToDb(product).collect { result ->
                _insertResult.value = result
            }
        }
    }
}