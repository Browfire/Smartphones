package com.example.smartphones

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartphones.model.SmartphoneDetails
import com.example.smartphones.model.SmartphoneItem
import com.example.smartphones.model.local.SmartphoneDataBase

class SmartphoneViewModel(application: Application): AndroidViewModel(application) {

    private val myRepository: SmartphoneRepository

    val allSmartphones: LiveData<List<SmartphoneItem>>
    val smartphoneSelection = MutableLiveData<Int>()

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

    fun smartphoneSelected (smartphoneId: Int){
        smartphoneSelection.value = smartphoneId
    }

}