package com.example.testcase.presentation.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.base.core.BaseFragment
import com.bumptech.glide.Glide
import com.example.testcase.R
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.databinding.FragmentProductDetailBinding
import com.example.testcase.databinding.FragmentProductMainFragmentBinding
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel


class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding,ProductDetailViewModel>(
    layoutId = R.layout.fragment_product_detail,
    viewModelClass = ProductDetailViewModel::class.java
) {
    override fun onInitDataBinding() {

        val product = arguments?.getParcelable<ProductUiModel>("product")
        if (product != null) {
            with(binding){
                topbarProductName.text = product.name
                productDetailName.text = product.name
                productDetailDescription.text = product.description
                price.text = product.price

                Glide.with(productDetailImage.context)
                    .load(product.image)
                    .into(productDetailImage)

                addToBasket.setOnClickListener {
                    Toast.makeText(context, "${getString(R.string.added_to_db)}  ${product.name}", Toast.LENGTH_SHORT).show()
                    saveToDb(product)
                }
            }
        }
        setListeners()
    }

    private fun saveToDb(product: ProductUiModel) {
        val dbProduct = DatabaseModel(product.id.toInt(),product.model,product.name,product.image,product.price,
            product.description,product.model,product.brand,product.createdAt)
        viewModel.insertToDb(dbProduct)
    }

    private fun setListeners() {
        with(binding){
            backArrow.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}