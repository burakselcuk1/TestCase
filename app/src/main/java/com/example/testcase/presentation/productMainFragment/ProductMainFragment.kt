package com.example.testcase.presentation.productMainFragment

import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.core.BaseFragment
import com.example.testcase.R
import com.example.testcase.common.Resource
import com.example.testcase.databinding.FragmentProductMainFragmentBinding
import com.example.testcase.presentation.productMainFragment.adapter.ProductAdapter
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductMainFragment : BaseFragment<FragmentProductMainFragmentBinding,ProductMainViewModel>(
    layoutId = R.layout.fragment_product_main_fragment,
    viewModelClass = ProductMainViewModel::class.java
) {
    private lateinit var productAdapter: ProductAdapter
    private var currentPageNumber = 1

    override fun onInitDataBinding() {
        viewModel.getProducts(1, LIMIT_PER_PAGE)
        setObservers()
        setListeners()
        recyclerviewScroolListener()
    }

    private fun setListeners() {
        binding.searchEditText.addTextChangedListener {text ->
            val query = text.toString().trim()
            viewModel.searchProducts(query)
            binding.productRcv.clearOnScrollListeners()
            binding.searchEditText.clearFocus()
        }
        setObserversForSearch()
    }

    private fun recyclerviewScroolListener() {
        binding.productRcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    fetchNextPage()
                }
            }
        })
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collect { resources ->
                when (resources) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val productList = resources.data
                        if (!productList.isNullOrEmpty()) {
                                productAdapter = ProductAdapter(productList.toMutableList())
                                val layoutManager = GridLayoutManager(requireContext(), 2)
                                binding.productRcv.layoutManager = layoutManager
                                binding.productRcv.adapter = productAdapter

                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), resources.toString(), Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setObserversForSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchProducts.collect { resources ->
                when (resources) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val productList = resources.data
                        if (!productList.isNullOrEmpty()) {
                            if (!::productAdapter.isInitialized) {
                                productAdapter = ProductAdapter(productList.toMutableList())
                                val layoutManager = GridLayoutManager(requireContext(), 2)
                                binding.productRcv.layoutManager = layoutManager
                                binding.productRcv.adapter = productAdapter
                            } else {
                                productAdapter.setData(productList)
                            }
                        }
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), resources.toString(), Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun fetchNextPage() {
        val nextPage = currentPageNumber + 1
        viewModel.getProducts(nextPage, LIMIT_PER_PAGE)
        currentPageNumber = nextPage
        binding.progressBar.visibility = View.VISIBLE

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        const val LIMIT_PER_PAGE = 8
    }
}

