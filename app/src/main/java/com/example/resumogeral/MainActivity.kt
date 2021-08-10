package com.example.resumogeral
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resumogeral.model.Adapter
import com.example.resumogeral.model.Products
import com.example.resumogeral.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<Products>> {

    private lateinit var recyclerView: RecyclerView
    private val adapter = Adapter()
    private val productsCall by lazy {
        RetrofitBuilder.getProductServices().getProducts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadComponents()
    }

    private fun loadComponents() {
        recyclerView = findViewById(R.id.productsRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private fun fetchProducts() {
        productsCall.clone().enqueue(this)
    }

    override fun onResume() {
        super.onResume()
        fetchProducts()
    }

    override fun onResponse(
        call: Call<List<Products>>,
        response: Response<List<Products>>
    ) {
        response.body()?.apply {
            adapter.update(this)
        }    }

    override fun onFailure(call: Call<List<Products>>, t: Throwable) {
    }


}
