package com.br.highbee.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import com.br.highbee.controller.CodeRegister
import com.br.highbee.controller.HomePageActivity
import com.br.highbee.controller.LoginActivity
import com.br.highbee.controller.WelcomePage
import com.br.highbee.model.User
import com.google.firebase.firestore.FirebaseFirestore


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

            binding.imageCard.load(Uri.parse(item.img)){
                placeholder(R.drawable.logo)
            }

            val db = FirebaseFirestore.getInstance()
            val crudUse = CRUD(binding.root.context)
            val pref = SharedPref(binding.root.context)

            binding.addProduct.setOnClickListener {
                val added = crudUse.createOrUpdateProduct(item.id, item)
                val prefProd = pref.findCache("products")

                if(prefProd != null){
                    Toast.makeText(binding.root.context, "${added} - ${prefProd}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}