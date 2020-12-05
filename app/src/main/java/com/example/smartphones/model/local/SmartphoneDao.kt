package com.example.smartphones.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartphones.model.SmartphoneDetails
import com.example.smartphones.model.SmartphoneItem


@Dao
interface SmartphoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSmartphoneItems(list: List<SmartphoneItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneSmartphoneDetail(details: SmartphoneDetails)

    @Query("SELECT * FROM smartphoneItem")
    fun getAllSmartphoneItems(): LiveData<List<SmartphoneItem>>

    @Query("SELECT * FROM smartphoneDetails WHERE id=:id")
    fun getOneSmartphoneDetails(id: Int): LiveData<SmartphoneDetails>

}
