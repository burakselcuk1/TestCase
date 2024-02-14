package com.example.testcase.presentation.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.core.BaseFragment
import com.base.core.BaseViewModel
import com.example.testcase.R
import com.example.testcase.databinding.FragmentBasketBinding

class BasketFragment : BaseFragment<FragmentBasketBinding, BasketViewModel>(
    layoutId = R.layout.fragment_basket,
    viewModelClass = BasketViewModel::class.java
) {
    override fun onInitDataBinding() {

    }
}