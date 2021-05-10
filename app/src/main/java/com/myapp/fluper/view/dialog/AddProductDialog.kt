package com.myapp.fluper.view.dialog

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.myapp.fluper.R
import com.myapp.fluper.database.entity.ProductData
import com.myapp.fluper.databinding.FragmentAddProductDialogBinding
import com.myapp.fluper.utils.toast
import com.myapp.fluper.viewmodel.AddProductViewModel
import kotlinx.android.synthetic.main.fragment_add_product_dialog.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL
import kotlin.coroutines.CoroutineContext


class AddProductDialog : DialogFragment() {

    lateinit var binding: FragmentAddProductDialogBinding
    lateinit var addProductViewModel: AddProductViewModel
    var data:ProductData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_product_dialog,
            container,
            false
        )
        addProductViewModel = ViewModelProviders.of(this).get(AddProductViewModel::class.java)

        val view: View = binding.root
        binding.btnDismiss.setOnClickListener {
            dialog!!.dismiss()
        }
        binding.btnAdd.setOnClickListener {
            if (validate()) {
                lifecycleScope.launch {
                    var id = null as Int?
                    if(data!=null)
                        id = data?.id!!
                    addProductViewModel.insertToDB(
                        ProductData(
                            id,
                            emailET.text.toString(),
                            et_desc.text.toString(),
                            et_r_price.text.toString().toInt(),
                            et_s_price.text.toString().toInt(),
                            getBitMap(),
                            0,
                            ""
                        )
                    )
                    dialog!!.dismiss()
                    context?.toast("data updated/inserted success")
                }
            }
        }
        if(arguments!=null && arguments!!.getSerializable("data") as ProductData !=null){
            data = arguments!!.getSerializable("data") as ProductData
            fillData(data!!)
            binding.btnAdd.text = "Update"
        }
        return view
    }

    private fun fillData(data:ProductData) {
        binding.emailET.setText(data.name ?: "")
        binding.etDesc.setText(data.description ?: "")
        binding.etRPrice.setText(data.regular_price.toString() ?: "")
        binding.etSPrice.setText(data.sale_price.toString() ?: "")
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    private fun validate(): Boolean {
        if (binding.emailET.text.isEmpty())
            return false
        return true
    }

    private suspend fun getBitMap(): Bitmap {
       val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data("https://picsum.photos/id/237/200/300")
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}