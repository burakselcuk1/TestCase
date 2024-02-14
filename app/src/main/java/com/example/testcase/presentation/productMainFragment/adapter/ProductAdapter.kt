package com.example.testcase.presentation.productMainFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testcase.R
import com.example.testcase.data.response.Products
import com.example.testcase.databinding.ProductItemLayoutBinding


class ProductAdapter(private val productList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(private val binding: ProductItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            with(binding) {
                Glide.with(productImage.context)
                    .load(product.image)
                    .placeholder(R.drawable.profile)
                    .into(productImage)
                textViewPrice.text = product.price
                textViewTitle.text = product.name
                textViewDescription.text = product.description
            }
        }
    }
}
