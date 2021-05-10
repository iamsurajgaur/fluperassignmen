package com.myapp.fluper.database.entity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "product")
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "regular_price")
    val regular_price: Int?,
    @ColumnInfo(name = "sale_price")
    val sale_price: Int?,
    @ColumnInfo(name = "product_photo")
    val product_photo: Bitmap?,
    @ColumnInfo(name = "colors")
    val colors: Int?,
    @ColumnInfo(name = "stores")
    val stores:String?
):Serializable