package com.example.smartphones

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.smartphones.model.SmartphoneDetails
import com.example.smartphones.model.SmartphoneItem
import com.example.smartphones.model.local.SmartphoneDataBase

class SmartphoneViewModel(application: Application): AndroidViewModel(application) {

    private val myRepository: SmartphoneRepository

    val allSmartphones: LiveData<List<SmartphoneItem>>

    init {
        val myDao = SmartphoneDataBase.getDatabase(application).smartphoneDao()
        myRepository = SmartphoneRepository(myDao)
        allSmartphones = myRepository.smartphoneList
        myRepository.getSmartphoneListFromApi()
    }

    fun getOneSmartphoneDetails(id: Int): LiveData<SmartphoneDetails>{
        myRepository.getSmartphoneDetailsFromApi(id)
        return myRepository.getSmartphoneDetails(id)
    }

}