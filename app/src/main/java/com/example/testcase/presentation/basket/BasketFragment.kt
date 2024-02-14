package com.example.testcase.presentation.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.core.BaseFragment
import com.base.core.BaseViewModel
import com.example.testcase.R
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.databinding.FragmentBasketBinding
import com.example.testcase.presentation.basket.adapter.DbAdapter
import com.example.testcase.presentation.productMainFragment.adapter.ProductAdapter
import kotlinx.coroutines.launch

class BasketFragment : BaseFragment<FragmentBasketBinding, BasketViewModel>(
    layoutId = R.layout.fragment_basket,
    viewModelClass = BasketViewModel::class.java
) {
    private lateinit var dbAdapter: DbAdapter
    override fun onInitDataBinding() {
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { data ->
            val totalPrice = calculateTotalPrice(data)
            binding.totalPrice.text = totalPrice.toString()

            dbAdapter = DbAdapter(data)
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.dbRecyclerview.layoutManager = layoutManager
            binding.dbRecyclerview.adapter = dbAdapter

        })
    }

    fun calculateTotalPrice(databaseModels: List<DatabaseModel>): Double {
        var totalPrice = 0.0

        for (item in databaseModels) {
            totalPrice += item.price.toDoubleOrNull() ?: 0.0
        }
        return totalPrice
    }
}