package com.example.testcase.data.response

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("price") val price: String,
    @SerializedName("description") val description: String,
    @SerializedName("model") val model: String,
    @SerializedName("brand") val brand: String,
    @SerializedName("createdAt") val createdAt: String
)

