package com.myapp.fluper.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.myapp.fluper.database.DataBaseUtil
import com.myapp.fluper.database.Database
import com.myapp.fluper.database.dao.ProductDao
import com.myapp.fluper.database.entity.ProductData

class ProductRepository(var context: Context) {

    //make the list of article Live data
    var allProducts: LiveData<List<ProductData>>
    var productDao: ProductDao

    init {
        val database= DataBaseUtil.invoke(context)
        productDao=database.getProductDao()
        allProducts = productDao.allProduct
    }

    fun getProduct(): LiveData<List<ProductData>> {
        return allProducts
    }

    fun updateProduct(productData: ProductData,id:Int) {
        productDao.update(productData)
    }
    fun deleteProduct(id:Int) {
        productDao.deleteId(id)
    }
}