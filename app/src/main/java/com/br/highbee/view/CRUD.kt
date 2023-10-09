package com.br.highbee.view

import android.content.Context
import com.br.highbee.models.ProductsBag
import com.br.highbee.models.ProductsHome
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


class CRUD(context: Context) {
    private val sharedPref = SharedPref(context)

     fun getProductsList(): MutableList<ProductsBag> {
        val productsListJson: String? = sharedPref.findCache("products")

        return if (productsListJson != null) {
            Json.decodeFromString(productsListJson)
        } else {
            mutableListOf()
        }
    }

    fun createOrUpdateProduct(id: Int, item: ProductsHome): String {
        try {
            val productsList = getProductsList()
            val product = productsList.find { it.id == id }

            if (product != null) {
                product.qtd++
            } else {
                val newProduct = ProductsBag(item.id, item.name, item.desc, item.img, item.price, 1)
                productsList.add(newProduct)
            }

            encodeJson(productsList)
            return "Produto adicionado na sacola"
        }catch(ex: Exception){
            return "Erro ao adicionar produto"
        }
    }

    fun deleteProduct(id: Int): String {
        try{
            val productsList = getProductsList()
            val product = productsList.find { it.id == id }
            var msg = ""
            if (product != null && product.qtd > 0) {
                product.qtd--

                msg = if (product.qtd == 0) {
                    productsList.remove(product)
                    encodeJson(productsList)
                    "Produto removido"
                } else {
                    encodeJson(productsList)
                    "Produto decrementado"
                }

                encodeJson(productsList)
            }
            return msg
        }catch(ex: Exception){
            return "Erro ao adicionar produto"
        }
    }

    private fun encodeJson(encodeList: List<ProductsBag>) {
        sharedPref.saveCache("products", Json.encodeToString(encodeList))
    }
}