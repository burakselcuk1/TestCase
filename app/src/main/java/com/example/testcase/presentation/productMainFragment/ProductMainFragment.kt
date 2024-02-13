package com.example.testcase.presentation.productMainFragment

import com.base.core.BaseFragment
import com.example.testcase.R
import com.example.testcase.databinding.FragmentProductMainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductMainFragment : BaseFragment<FragmentProductMainFragmentBinding,ProductMainViewModel>(
    layoutId = R.layout.fragment_product_main_fragment,
    viewModelClass = ProductMainViewModel::class.java
) {
    override fun onInitDataBinding() {
        binding.buton.setOnClickListener {
            viewModel.getProducts()

        }
    }
}