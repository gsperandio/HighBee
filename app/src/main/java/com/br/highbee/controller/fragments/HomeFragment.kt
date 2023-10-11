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
        ProductsHome(1,"Colombian Gold","Desc de um produto", "https://cdn.dribbble.com/userupload/5828897/file/original-3918d15943c4ffbebec75cbada1f661a.jpg?resize=200x200", 75.99),
        ProductsHome(2,"AK-47", "Desc de um produto", "https://cdn.dribbble.com/userupload/8969527/file/original-ba35a2a2782fa4758bd2ee534144b5a7.png?resize=200x200", 42.50),
        ProductsHome(3,"Orange Kush", "Desc de um produto", "https://cdn.dribbble.com/userupload/9674824/file/original-332e78f50133d0e82877e6e94401693b.jpg?resize=200x200", 99.90),
        ProductsHome(4,"Purple Haze", "Desc de um produto", "https://cdn.dribbble.com/userupload/8806891/file/original-5b13d8eff42197ddc1825c94b6109729.png?resize=200x200", 54.75),
        ProductsHome(5,"Blue Dream", "Desc de um produto", "https://cdn.dribbble.com/userupload/7785887/file/original-f8a4ebbf13df653591d9582562412588.png?resize=200x200", 31.25),
        ProductsHome(6,"Sour Diesel","Desc de um produto", "https://cdn.dribbble.com/userupload/6448178/file/original-718011f5e886d454c71659fa3702c99f.jpg?resize=200x200", 68.00),
        ProductsHome(7,"OG Kush", "Desc de um produto", "https://cdn.dribbble.com/userupload/4913742/file/original-db72d36e7ee983b3e3a5a606d5a11f4a.jpg?resize=200x200", 89.99),
        ProductsHome(8,"White Widow", "Desc de um produto", "https://cdn.dribbble.com/users/642793/screenshots/16734553/media/6a6ef2de78e3f7c76167c2d74d519be5.png?resize=200x200", 45.60),
        ProductsHome(9,"Girl Scout Cookies", "Desc de um produto", "https://cdn.dribbble.com/users/1358460/screenshots/14010929/media/57a1f99a9ea4e158453e3e95eb2f6291.jpg?resize=200x200", 79.50),
        ProductsHome(10,"Pineapple Express", "Desc de um produto", "https://cdn.dribbble.com/users/1633085/screenshots/16865805/media/9a1c0e3c73dafd45c8a2804abdc21219.jpg?resize=200x200&vertical=center", 22.75),
        ProductsHome(11,"Northern Lights", "Desc de um produto", "https://cdn.dribbble.com/userupload/2706158/file/original-141167bbbdfe5c4ccb45d12143cbb7db.jpg?resize=200x200", 59.99),
        ProductsHome(12,"Green Crack", "Desc de um produto", "https://cdn.dribbble.com/userupload/10101238/file/original-e67a5ba30b5c84912b7bbf6fe9e832cb.png?resize=200x200", 38.25)
    )
}