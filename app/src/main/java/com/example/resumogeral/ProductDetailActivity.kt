package com.example.resumogeral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.resumogeral.model.Products
import com.example.resumogeral.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity(), Callback<Products>{

    private lateinit var productImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var priceTextView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        loadComponents()

       loadProductDetail()


    }

    private fun loadProductDetail() {
        val productId = intent.getIntExtra(PARAMETER_PRODUCT, 0)
        val call = RetrofitBuilder.getProductServices().getProduct(productId)
        call.clone().enqueue(this)
    }


    private fun loadComponents(){
        productImageView = findViewById(R.id.imageDetails)
        titleTextView = findViewById(R.id.idTitle)
        descriptionTextView = findViewById(R.id.idDescription)
        priceTextView = findViewById(R.id.idPrice)
    }

    private fun bind(product: Products){
        titleTextView.text = product.title
        descriptionTextView.text = product.description
        priceTextView.text = "R$ ${product.price}"

        Glide.with(this)
            .load(product.image)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(productImageView)
    }

    companion object {
        const val PARAMETER_PRODUCT = "parameter_product_key"
    }

    override fun onResponse(call: Call<Products>, response: Response<Products>) {
        response.body()?.apply {
            bind(this)
        }
    }

    override fun onFailure(call: Call<Products>, t: Throwable) {
        println("Falha no carregamento do produto")
    }


}