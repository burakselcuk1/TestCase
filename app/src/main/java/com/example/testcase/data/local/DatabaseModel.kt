package com.example.testcase.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "product_database")
@Parcelize
data class DatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val dbId : Int = 0,
    val id: String,
    val name: String,
    val image: String,
    val price: String,
    val description: String,
    val model: String,
    val brand: String,
    val createdAt: String
) : Parcelable