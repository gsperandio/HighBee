package com.example.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.highbee.R
import com.example.highbee.databinding.ActivityMainBinding
import com.example.highbee.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.register.setOnClickListener {
            val intent = Intent(this, CodeRegister::class.java)
            startActivity(intent)
        }
    }
}