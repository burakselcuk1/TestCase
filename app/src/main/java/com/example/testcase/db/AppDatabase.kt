package com.example.testcase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel

@Database(entities = [DatabaseModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun databaseDao(): Dao

}