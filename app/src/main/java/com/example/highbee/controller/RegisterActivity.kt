package com.example.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import com.example.highbee.databinding.ActivityRegisterBinding
import com.example.highbee.view.DoneToNext
import com.example.highbee.view.UpperFirst

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

        binding.nameUser.addTextChangedListener(UpperFirst(binding.nameUser))
        binding.lastnameUser.addTextChangedListener(UpperFirst(binding.lastnameUser))
        binding.nameUser.setOnEditorActionListener(DoneToNext(binding.nameUser, binding.lastnameUser))
        binding.lastnameUser.setOnEditorActionListener(DoneToNext(binding.lastnameUser, binding.celphone))
    }
}