package com.example.smartphones.model
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class SmartphoneItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
    )