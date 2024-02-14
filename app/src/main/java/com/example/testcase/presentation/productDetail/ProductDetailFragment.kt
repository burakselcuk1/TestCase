package com.example.testcase.presentation.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.core.BaseFragment
import com.example.testcase.R
import com.example.testcase.databinding.FragmentProductDetailBinding
import com.example.testcase.databinding.FragmentProductMainFragmentBinding
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel


class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding,ProductDetailViewModel>(
    layoutId = R.layout.fragment_product_detail,
    viewModelClass = ProductDetailViewModel::class.java
) {
    override fun onInitDataBinding() {

        val productName = arguments?.getParcelable<ProductUiModel>("product")
        if (productName != null) {
            binding.tv.text = productName.name.toString()
        }
    }
}