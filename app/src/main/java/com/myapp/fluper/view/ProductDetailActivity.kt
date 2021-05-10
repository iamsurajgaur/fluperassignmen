package com.myapp.fluper.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import coil.load
import com.myapp.fluper.R
import com.myapp.fluper.database.DataBaseUtil
import com.myapp.fluper.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_detail)
        val database= DataBaseUtil.invoke(this)
        val productDao=database.getProductDao()
        val productdata = productDao.getProductById(intent.getIntExtra("id",0))
        binding.data = productdata
        binding.iv.load(productdata.product_photo)
    }
}