package com.example.testcase.presentation.productMainFragment.model

import android.os.Parcelable
import com.example.testcase.data.response.Products
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ProductUiModel(
@SerializedName("id") val id: String,
@SerializedName("name") val name: String,
@SerializedName("image") val image: String,
@SerializedName("price") val price: String,
@SerializedName("description") val description: String,
@SerializedName("model") val model: String,
@SerializedName("brand") val brand: String,
@SerializedName("createdAt") val createdAt: String
) : Parcelable
