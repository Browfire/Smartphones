package com.example.smartphones.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartphones.model.SmartphoneDetails
import com.example.smartphones.model.SmartphoneItem


@Database(entities = [SmartphoneDetails::class, SmartphoneItem::class], version = 1)
abstract class SmartphoneDataBase: RoomDatabase() {

    abstract fun smartphoneDao(): SmartphoneDao

    companion object {

        @Volatile
        private var INSTANCE: SmartphoneDataBase? = null

        fun getDatabase(context: Context): SmartphoneDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmartphoneDataBase::class.java,
                    "mansion_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}