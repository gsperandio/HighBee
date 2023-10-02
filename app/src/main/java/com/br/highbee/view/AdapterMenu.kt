package com.br.highbee.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.highbee.R
import com.br.highbee.databinding.ItemMenuBinding
import com.br.highbee.models.MenuItem

class AdapterMenu(
    private val myList: List<MenuItem>
) : RecyclerView.Adapter<AdapterMenu.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myList[position]
        holder.bind(item)
    }

    inner class MyViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MenuItem) {
            binding.textMenu.text = item.name
            binding.iconMenu.setImageResource(item.icon)
        }
    }
}
