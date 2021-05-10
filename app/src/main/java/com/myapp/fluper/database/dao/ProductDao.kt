package com.myapp.fluper.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.myapp.fluper.database.entity.ProductData

@Dao
interface ProductDao {
    @get:Query("SELECT * from product")
    val allProduct: LiveData<List<ProductData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductData>)

    @Query("DELETE FROM product")
    fun deleteAll()

    @Query("DELETE FROM product where id= :id")
    fun deleteId(id:Int)

    @Query("SELECT * from product where id= :id")
    fun getProductById(id:Int):ProductData

    @Update
    fun update(productData: ProductData)
}