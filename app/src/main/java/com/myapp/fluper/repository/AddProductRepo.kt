package com.myapp.fluper.repository

import android.content.Context
import com.myapp.fluper.database.DataBaseUtil
import com.myapp.fluper.database.dao.ProductDao
import com.myapp.fluper.database.entity.ProductData

class AddProductRepo(val context: Context) {

    var productDao: ProductDao
    init {
        val database= DataBaseUtil.invoke(context)
        productDao=database.getProductDao()
    }

    fun insertProduct(productData: ProductData) {
        productDao.insert(productData)
    }
}