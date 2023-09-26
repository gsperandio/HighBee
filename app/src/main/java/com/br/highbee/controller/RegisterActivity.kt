package com.br.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import com.br.highbee.databinding.ActivityRegisterBinding
import com.br.highbee.view.DoneToNext
import com.br.highbee.view.UpperFirst
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val firestore = FirebaseFirestore.getInstance()
        binding.register.setOnClickListener {
//            val data = hashMapOf(
//                "FirstName" to binding.nameUser.text.toString(),
//                "LastName" to binding.lastnameUser.text.toString(),
//                "Phone" to binding.celphone.text.toString()
//            )

            //firestore.collection("users").document("LA").set(data)
            val intent = Intent(this, CodeRegister::class.java)
           startActivity(intent)
        }

        binding.nameUser.addTextChangedListener(UpperFirst(binding.nameUser))
        binding.lastnameUser.addTextChangedListener(UpperFirst(binding.lastnameUser))
        binding.nameUser.setOnEditorActionListener(DoneToNext(binding.nameUser, binding.lastnameUser))
        binding.lastnameUser.setOnEditorActionListener(DoneToNext(binding.lastnameUser, binding.celphone))
    }
}