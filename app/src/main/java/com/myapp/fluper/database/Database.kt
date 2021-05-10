package com.myapp.fluper.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.myapp.fluper.database.dao.ProductDao
import com.myapp.fluper.database.entity.ProductData

@Database(entities = arrayOf(ProductData::class),version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase()   {
    abstract fun getProductDao(): ProductDao
}