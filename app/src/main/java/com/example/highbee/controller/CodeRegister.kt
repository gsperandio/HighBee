package com.example.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.highbee.R
import com.example.highbee.databinding.ActivityCodeRegisterBinding

class CodeRegister : AppCompatActivity() {
    private lateinit var binding: ActivityCodeRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resend.setOnClickListener {
            val intent = Intent(this, TermsOfUse::class.java)
            startActivity(intent)
        }

        binding.returningButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}