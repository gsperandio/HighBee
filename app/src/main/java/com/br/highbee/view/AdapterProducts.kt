package com.br.highbee.view

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.highbee.databinding.ItemCardBinding
import com.br.highbee.models.ProductsHome
import java.net.URL
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.br.highbee.R


class AdapterProducts(
    private val myList: List<ProductsHome>
) : RecyclerView.Adapter<AdapterProducts.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProducts.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = myList.size
    override fun onBindViewHolder(holder: AdapterProducts.MyViewHolder, position: Int) {
        val item = myList[position]
        holder.bind(item)
    }

    inner class MyViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductsHome) {
            binding.title.text = item.name
            binding.price.text = "R$  ${item.price}"
            val uri = Uri.parse(item.img)
            binding.imageCard.load(uri){
                placeholder(R.drawable.logo)
            }

            binding.addProduct.setOnClickListener {
                Toast.makeText(itemView.context, "${item.name} with coast ${item.price}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun showToast(message: String) {

    }
}