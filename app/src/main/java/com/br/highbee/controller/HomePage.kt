package com.br.highbee.controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.br.highbee.R
import com.br.highbee.databinding.ActivityHomePageBinding
import com.br.highbee.view.SharedPref
import com.google.gson.Gson
import com.br.highbee.models.MenuItem

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initNavigation()
        initBadge()
        initOptions(this)
    }

    private fun initNavigation(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController();
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    private fun initBadge(){
        var badge = binding.bottomNav.getOrCreateBadge(R.id.menu_cart)

        badge.isVisible = false
        badge.number = 99
        badge.backgroundColor = getColor(R.color.yellowStrong)
        badge.badgeTextColor = getColor(R.color.black)
        //badge.horizontalOffset = 15
    }

    private fun initOptions(context: Context){
        val sharedPref = SharedPref(context)
        val gson = Gson()

        val menuItem1 = MenuItem("Perfil", "ic_smile_fill.xml")
        val menuItem2 = MenuItem("Endereços", "ic_map_fill.xml")
        val menuItem3 = MenuItem("Carteira", "ic_card_fill.xml")
        val menuItem4 = MenuItem("Favoritos", "ic_favorite_hand_fill.xml")
        val menuItem5 = MenuItem("Ajuda", "question_fill.xml")
        val listMenu = listOf(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5)

        sharedPref.saveCache("menu", gson.toJson(listMenu))

        val lista = gson.fromJson(gson.toJson(listMenu), List::class.java)
    }

}