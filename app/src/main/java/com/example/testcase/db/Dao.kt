package com.example.testcase.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.testcase.data.local.DatabaseModel
import com.example.testcase.presentation.productMainFragment.model.ProductUiModel

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDb(dbValue: DatabaseModel)

    @Query("SELECT * FROM product_database ORDER BY dbId ASC")
    fun getAllDbValues(): LiveData<List<DatabaseModel>>
}