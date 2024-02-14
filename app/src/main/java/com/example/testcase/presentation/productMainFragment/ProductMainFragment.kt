package com.example.testcase.presentation.productMainFragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.base.core.BaseFragment
import com.example.testcase.R
import com.example.testcase.common.Resource
import com.example.testcase.databinding.FragmentProductMainFragmentBinding
import com.example.testcase.presentation.productMainFragment.adapter.ProductAdapter
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductMainFragment : BaseFragment<FragmentProductMainFragmentBinding,ProductMainViewModel>(
    layoutId = R.layout.fragment_product_main_fragment,
    viewModelClass = ProductMainViewModel::class.java
) {
    private lateinit var productList: ProductUiModel
    private lateinit var productAdapter: ProductAdapter

    override fun onInitDataBinding() {
        viewModel.getProducts()
        setObservers()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collect{resources ->
                when(resources){
                    is Resource.Loading ->{
                        with(binding){
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is Resource.Success -> {
                        val productList = resources.data
                            if (resources.data.isNullOrEmpty()){
                                Toast.makeText(requireContext(), "bos", Toast.LENGTH_SHORT).show()
                            }else{
                                with(binding) {
                                    progressBar.visibility = View.GONE
                                   val productAdapter = ProductAdapter(resources.data)
                                    val layoutManager = GridLayoutManager(requireContext(), 2) // 2 sütunlu bir grid oluşturuyoruz
                                    binding.productRcv.layoutManager = layoutManager
                                    binding.productRcv.adapter = productAdapter
                                }
                            }
                    }
                    is Resource.Error ->{
                        Toast.makeText(requireContext(),getString(R.string.error),Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                    }
                }
            }
        }
    }
}