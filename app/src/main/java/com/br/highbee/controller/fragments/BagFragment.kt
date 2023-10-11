package com.br.highbee.controller.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.highbee.databinding.FragmentBagBinding
import com.br.highbee.models.ProductsBag
import com.br.highbee.view.AdapterBag
import com.br.highbee.view.AdapterMenu
import com.br.highbee.view.CRUD
import com.br.highbee.view.SharedPref
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BagFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BagFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBagBinding
    private lateinit var adapterBag: AdapterBag
    private var list: MutableList<ProductsBag> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBagBinding.inflate(inflater, container, false)
        initRecyclerView()
        binding.finish.setOnClickListener {
            FinishyBaby()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BagFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BagFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun initRecyclerView(){

        list = CRUD(requireContext()).getProductsList()
        list.reverse()

        if (list.isEmpty()){
            binding.animationBag.visibility = View.VISIBLE
            binding.recyclerViewBagProducts.visibility = View.INVISIBLE
            binding.header.visibility = View.INVISIBLE
        }else{
            binding.totalPrice.text = totalPrice(list)
            binding.recyclerViewBagProducts.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewBagProducts.setHasFixedSize(true)
            adapterBag = AdapterBag(list, binding.totalPrice)
            binding.recyclerViewBagProducts.adapter = adapterBag
            binding.animationBag.visibility = View.INVISIBLE
            binding.recyclerViewBagProducts.visibility = View.VISIBLE
            binding.header.visibility = View.VISIBLE
        }
    }


    private fun FinishyBaby(){
        val db = FirebaseFirestore.getInstance()
        val dbColl = db.collection("products")
        val sharedPref = SharedPref(requireContext())
        val items = CRUD(requireContext()).getProductsList()
        val phoneCache: String? = sharedPref.findCache("phone")

        val productMap = mutableMapOf<String, ProductsBag>()
        items.forEach { product ->
            productMap[product.id.toString()] = product
        }

        dbColl.document(phoneCache.toString()).set(productMap)
        .addOnSuccessListener {
            Toast.makeText(requireContext(), "Compra finalizada!!!", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
            Toast.makeText(requireContext(), "Erro ao comprar produtos", Toast.LENGTH_SHORT).show()
        }

        sharedPref.removeCache("products")
        initRecyclerView()
    }

    private fun totalPrice(list: MutableList<ProductsBag>): String {
        var total: Double = 0.0

        for (item in list) {
            val subtotal = item.qtd * item.price
            total += subtotal
        }

        return "R$ %.2f".format(total)
    }


}