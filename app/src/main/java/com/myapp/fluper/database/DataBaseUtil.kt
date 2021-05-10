package com.myapp.fluper.database

import android.content.Context
import androidx.room.Room

class DataBaseUtil {
    //Singleton object for Database through out the app
    companion object {
        @Volatile private var instance: Database? = null
        private val LOCK = Any()  // Any means Object Type

        fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            Database::class.java, "ProductsDB.db")
            .allowMainThreadQueries()
            .build()
    }
}