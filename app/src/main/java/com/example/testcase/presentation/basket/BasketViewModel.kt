package com.example.testcase.presentation.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.base.core.BaseViewModel
import com.example.testcase.common.Resource
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.basket.model.BasketUiModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import com.example.testcase.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketUseCase: BasketUseCase,
): BaseViewModel() {

    private val _insertResult = MutableStateFlow<Resource<BasketUiModel>>(Resource.Loading)
    val insertResult: MutableStateFlow<Resource<BasketUiModel>> = _insertResult

    val readAllData: LiveData<List<DatabaseModel>> = basketUseCase.readAllData

    fun insertToDb(isim: DatabaseModel) {
        viewModelScope.launch {
            basketUseCase.insertToDb(isim).collect { result ->
                _insertResult.value = result
            }
        }
    }
}