package com.myapp.fluper.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.myapp.fluper.R
import com.myapp.fluper.database.entity.ProductData
import com.myapp.fluper.databinding.ItemProductBinding
import com.myapp.fluper.view.ProductListActivity
import com.myapp.fluper.view.dialog.AddProductDialog
import com.myapp.fluper.viewmodel.ProductViewModel

class ProductListAdapter(
    var products: List<ProductData>?,
    val viewModel: ProductViewModel,
    val activity: ProductListActivity
) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_product, parent, false
        )
        //in xml each variable in binding has setter getter itself and that setter takes the same variable as a parameter
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    fun updateList(articles: List<ProductData>?) {
        this.products = articles
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product = products?.get(position)
        holder.binding.articleImage.load(products?.get(position)?.product_photo)
        holder.binding.btnDelete.setOnClickListener {
            viewModel.deleteWhereId(products?.get(position)?.id!!)
        }
        holder.binding.btnUpdate.setOnClickListener {
            openUpdateDialog(position)
        }
        holder.binding.cvRoot.setOnClickListener {
            activity.startProductDetailActivity(products?.get(position))
        }
        holder.binding.executePendingBindings()
    }

    private fun openUpdateDialog(pos:Int) {
//        viewModel.updateToDB()
        val fragment = AddProductDialog()
        val bundle = Bundle()
        bundle.putSerializable("data",products?.get(pos))
        fragment.arguments = bundle
        fragment.show(activity.supportFragmentManager, "")
    }

    //binding.root returns view which is inflating
    class ProductViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)
}

