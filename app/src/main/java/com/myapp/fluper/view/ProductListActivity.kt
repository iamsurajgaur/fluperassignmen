package com.myapp.fluper.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.fluper.R
import com.myapp.fluper.database.entity.ProductData
import com.myapp.fluper.databinding.ActivityProductListBinding
import com.myapp.fluper.view.adapter.ProductListAdapter
import com.myapp.fluper.viewmodel.ProductViewModel

class ProductListActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductListBinding
    lateinit var adapter : ProductListAdapter
    lateinit var allProductData: LiveData<List<ProductData>>
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_list)
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        fetchProductFromDB()
        populateRV()
    }

    private fun fetchProductFromDB() {
        allProductData = productViewModel.getAllArticlesFromLocal()
        setObserver()
    }

    private fun setObserver() {
        allProductData.observe(this,object : Observer<List<ProductData>> {
            override fun onChanged(t: List<ProductData>?) {
                adapter.updateList(t)
            }
        })
    }
    fun startProductDetailActivity(data: ProductData?) {
        val intent = Intent(this,ProductDetailActivity::class.java)
        intent.putExtra("id",data?.id)
        startActivity(intent)
    }

    private fun populateRV() {
        adapter = ProductListAdapter(allProductData.value,productViewModel,this)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(this@ProductListActivity)
    }
}