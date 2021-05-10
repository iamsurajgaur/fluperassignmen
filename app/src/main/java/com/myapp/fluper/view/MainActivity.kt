package com.myapp.fluper.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.myapp.fluper.R
import com.myapp.fluper.databinding.ActivityMainBinding
import com.myapp.fluper.view.dialog.AddProductDialog

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.btnShowProduct.setOnClickListener {
            Intent(this,ProductListActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.btnCreateProduct.setOnClickListener {
//            supportFragmentManager.beginTransaction().show(AddProductDialog()).commit()
            AddProductDialog().show(supportFragmentManager,"")
        }
    }
}