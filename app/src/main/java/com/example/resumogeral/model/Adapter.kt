package com.example.resumogeral.model

import android.system.Os.bind
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.resumogeral.R

class Adapter (val onClick: (Products) -> Unit): RecyclerView.Adapter<ItensProducts>() {

    private var listOfProducts = mutableListOf<Products>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItensProducts {
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false).apply {
            return ItensProducts(this)
        }
    }

    override fun onBindViewHolder(holder: ItensProducts, position: Int) {
        listOfProducts[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener { onClick(this) }
        }
    }

    override fun getItemCount(): Int {
        return listOfProducts.size

    }

    fun update(newList: List<Products>) {
        listOfProducts = mutableListOf()
        listOfProducts.addAll(newList)
        notifyDataSetChanged()
    }

}

class ItensProducts(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val productImageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleTextView = itemView.findViewById<TextView>(R.id.idTitle)
    private val princeTextView = itemView.findViewById<TextView>(R.id.idPrice)
    private val descriptionTextView = itemView.findViewById<TextView>(R.id.idDescription)

    fun bind(product: Products) {
        titleTextView.text = product.title
        descriptionTextView.text = product.description
        princeTextView.text = "R$ ${product.price}"

        Glide.with(itemView.context)
            .load(product.image)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(productImageView)

    }
}