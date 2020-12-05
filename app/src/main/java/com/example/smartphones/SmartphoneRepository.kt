package com.example.smartphones

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.smartphones.model.SmartphoneDetails
import com.example.smartphones.model.SmartphoneRetrofitClient
import com.example.smartphones.model.local.SmartphoneDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmartphoneRepository(private val mySmartphoneDao: SmartphoneDao) {

    private val myRetrofit = SmartphoneRetrofitClient.retrofitInstance()

    // get smartphone list from database
    val smartphoneList = mySmartphoneDao.getAllSmartphoneItems()

    // get smartphone details from database
    fun getSmartphoneDetails(id: Int): LiveData<SmartphoneDetails>{
        return mySmartphoneDao.getOneSmartphoneDetails(id)
    }

    // get smartphone list from API
    fun getSmartphoneListFromApi()= CoroutineScope(Dispatchers.IO).launch{

        val service= kotlin.runCatching { myRetrofit.fetchAllSmartphones() }

        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    mySmartphoneDao.insertAllSmartphoneItems(it)    // save items in database

                }
                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())
        }

    }

    // get smartphone details from API
    fun getSmartphoneDetailsFromApi(id: Int)= CoroutineScope(Dispatchers.IO).launch{

        val service= kotlin.runCatching { myRetrofit.fetchOneSmartphone(id) }

        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {

                    mySmartphoneDao.insertOneSmartphoneDetail(it)   // save items in database

                }
                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }

        service.onFailure {
            Log.e("ERROR", it.message.toString())
        }

    }

}