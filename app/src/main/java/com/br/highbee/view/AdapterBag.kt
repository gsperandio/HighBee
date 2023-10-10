package com.br.highbee.view

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.highbee.R
import com.br.highbee.databinding.ItemBagBinding
import com.br.highbee.model.User
import com.br.highbee.models.MenuItem
import com.br.highbee.models.ProductsBag
import com.br.highbee.models.ProductsHome
class AdapterBag(private  val myList: MutableList<ProductsBag>) : RecyclerView.Adapter<AdapterBag.MyViewHolder>() {
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
        val crud = CRUD(binding.root.context)
        fun bind(item: ProductsBag){
            val produtoDesnecessauro = ProductsHome(
                item.id,
                item.name,
                item.desc,
                item.img,
                item.price
            )

            binding.titleItemBag.text = item.name
            binding.subtitleItemBag.text = item.desc
            binding.qtItem.text = item.qtd.toString()
            binding.imageCard.load(Uri.parse(item.img)){
                placeholder(R.drawable.logo)
            }
            binding.priceItem.text = "R$ %.2f".format(item.qtd * item.price)

            binding.deleteProduct.setOnClickListener {
                crud.removeProduct(item.id)
                val novaLista = crud.getProductsList()


            }

            binding.addProduct.setOnClickListener {

                crud.createOrUpdateProduct(item.id, produtoDesnecessauro)

            }

        }

        fun atualizarLista(novaLista: List<ProductsBag>) {
            myList.clear()
            myList.addAll(novaLista)

        }
    }

}