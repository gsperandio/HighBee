package com.example.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.highbee.R
import com.example.highbee.databinding.ActivityWelcomePageBinding

class WelcomePage : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.haveAccess.setOnClickListener {
            showToast()
        }

        binding.dontHaveAccess.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String = "Não adianta clicar aqui, ninguem tem conta nesse APP ainda!") {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}