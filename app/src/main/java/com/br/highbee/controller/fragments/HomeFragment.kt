package com.br.highbee.controller.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.br.highbee.R
import com.br.highbee.databinding.FragmentHomeBinding
import com.br.highbee.models.MenuItem
import com.br.highbee.models.ProductsHome
import com.br.highbee.view.AdapterMenu
import com.br.highbee.view.AdapterProducts
import java.math.BigDecimal

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentHomeBinding

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initRecyclerView() {
        binding.recyclerViewHomeProducts.setHasFixedSize(true)
        binding.recyclerViewHomeProducts.adapter =  AdapterProducts(getList())
    }

    private fun getList() = listOf(
        ProductsHome(1,"Colombian Gold", "https://cdn.dribbble.com/users/642793/screenshots/16922447/media/bace1bf7b4d7317d21051e0029cc3287.png", 75.99),
        ProductsHome(2,"AK-47", "https://cdn.dribbble.com/userupload/3162010/file/original-a2d1e344c3a3edd66ccec191400ca6d8.jpg", 42.50),
        ProductsHome(3,"Orange Kush", "https://cdn.dribbble.com/userupload/3162007/file/original-84419bd0d5a63966ecbfaeae1ece6316.jpg", 99.90),
        ProductsHome(4,"Purple Haze", "https://cdn.dribbble.com/users/10132200/screenshots/18510100/media/87c06e52c63d56d4f8a7bad3d1b2050a.jpg", 54.75),
        ProductsHome(5,"Blue Dream", "https://cdn.dribbble.com/userupload/3161986/file/original-2e448b501f9efd6cdafd7848b32a7a76.jpg", 31.25),
        ProductsHome(6,"Sour Diesel", "https://cdn.dribbble.com/userupload/3161983/file/original-b8fec78e95bda228ff68dbdc8e3c13c8.jpg", 68.00),
        ProductsHome(7,"OG Kush", "https://cdn.dribbble.com/users/642793/screenshots/16562527/media/a605bbde6a42707165088c9040ee6925.png", 89.99),
        ProductsHome(8,"White Widow", "https://cdn.dribbble.com/userupload/8363870/file/original-97cf36b44f748e3b071e21b7a49750bf.png", 45.60),
        ProductsHome(9,"Girl Scout Cookies", "https://cdn.dribbble.com/users/214940/screenshots/1993323/media/ad45a60582e32cac16c97de04f5bba22.jpg", 79.50),
        ProductsHome(10,"Pineapple Express", "https://cdn.dribbble.com/userupload/3482515/file/original-0dc05032bd7fbf058a755d814c345e5e.jpg", 22.75),
        ProductsHome(11,"Northern Lights", "https://cdn.dribbble.com/users/3486994/screenshots/11531422/media/4b1d25dbc992c771134dc19e548e4ff7.png", 59.99),
        ProductsHome(12,"Green Crack", "https://cdn.dribbble.com/userupload/8806891/file/original-5b13d8eff42197ddc1825c94b6109729.png", 38.25)
    )
}