package com.example.testcase.presentation.productMainFragment.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testcase.R
import com.example.testcase.data.response.Products
import com.example.testcase.databinding.ProductItemLayoutBinding
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel

class ProductAdapter(private val productList: MutableList<ProductUiModel>,
                     private val onButtonClick: (ProductUiModel) -> Unit) :
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

    fun addNewData(newData: List<ProductUiModel>) {
        productList.addAll(newData)
        notifyDataSetChanged()
    }

    fun setData(newData: List<ProductUiModel>) {
        productList.clear()
        productList.addAll(newData)
        notifyDataSetChanged()
    }


    inner class ProductViewHolder(private val binding: ProductItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductUiModel) {
            with(binding) {
                Glide.with(productImage.context)
                    .load(product.image)
                    .placeholder(R.drawable.profile)
                    .into(productImage)
                textViewPrice.text = product.price
                textViewTitle.text = product.name
                binding.buttonAddToCart.setOnClickListener {
                    onButtonClick(product)
                }
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = productList[position]
                    val bundle = Bundle().apply {
                        putParcelable("product", clickedItem)
                    }
                    itemView.findNavController().navigate(
                        R.id.action_productMainFragment_to_productDetailFragment,
                        bundle
                    )
                }
            }
        }
    }
}
