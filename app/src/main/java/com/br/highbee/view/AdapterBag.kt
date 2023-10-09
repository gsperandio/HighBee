package com.br.highbee.view

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.highbee.R
import com.br.highbee.databinding.ItemBagBinding
import com.br.highbee.models.ProductsBag

class AdapterBag(private  val myList: List<ProductsBag>) : RecyclerView.Adapter<AdapterBag.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBag.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBagBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myList[position]
        holder.bind(item)
    }

    inner class MyViewHolder(private val binding: ItemBagBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: ProductsBag){
            binding.titleItemBag.text = item.name
            binding.subtitleItemBag.text = item.desc
            binding.qtItem.text = item.qtd.toString()
            binding.imageCard.load(Uri.parse(item.img)){
                placeholder(R.drawable.logo)
            }
            binding.priceItem.text = ("R$ %.2f".format(Math.round(item.qtd * item.price)))
        }

    }

}