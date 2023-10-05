package com.br.highbee.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.br.highbee.R
import com.br.highbee.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {

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
    }

    private fun initNavigation(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController();
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    private fun initBadge(){
        var badge = binding.bottomNav.getOrCreateBadge(R.id.menu_cart)

        badge.isVisible = true
        badge.number = 99
        badge.backgroundColor = getColor(R.color.yellowStrong)
        badge.badgeTextColor = getColor(R.color.black)
        //badge.horizontalOffset = 15
    }

}