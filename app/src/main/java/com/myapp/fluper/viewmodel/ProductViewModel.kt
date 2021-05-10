package com.myapp.fluper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.myapp.fluper.database.entity.ProductData
import com.myapp.fluper.repository.ProductRepository

class ProductViewModel(application: Application):AndroidViewModel(application) {

    var productRepository:ProductRepository
    init {
        productRepository=ProductRepository(application)
    }
    fun getAllArticlesFromLocal(): LiveData<List<ProductData>> {
        return productRepository.getProduct()
    }

    fun updateToDB(productData: ProductData,id:Int) {
        return productRepository.updateProduct(productData,id)
    }
    fun deleteWhereId(id:Int) {
        return productRepository.deleteProduct(id)
    }
}