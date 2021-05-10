package com.myapp.fluper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.myapp.fluper.database.entity.ProductData
import com.myapp.fluper.repository.AddProductRepo
import com.myapp.fluper.repository.ProductRepository

class AddProductViewModel(application: Application): AndroidViewModel(application) {

    var addProductRepo: AddProductRepo
    init {
        addProductRepo= AddProductRepo(application)
    }
    fun insertToDB(productData: ProductData) {
        addProductRepo.insertProduct(productData)
    }
}