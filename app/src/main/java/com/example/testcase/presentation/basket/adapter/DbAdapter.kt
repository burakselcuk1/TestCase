package com.example.testcase.presentation.basket.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testcase.R
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.databinding.DbItemLayoutBinding
import com.example.testcase.databinding.ProductItemLayoutBinding
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel


class DbAdapter(private val productList: List<DatabaseModel>) :
    RecyclerView.Adapter<DbAdapter.DbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DbViewHolder {
        val binding = DbItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DbViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    inner class DbViewHolder(private val binding: DbItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: DatabaseModel) {
            with(binding) {
                brandName.text = product.name
                price.text = product.price
            }
        }
    }
}