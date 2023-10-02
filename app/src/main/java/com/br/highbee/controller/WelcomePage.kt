package com.br.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.br.highbee.R
import com.br.highbee.databinding.ActivityWelcomePageBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.lang.RuntimeException

class WelcomePage : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        binding.haveAccess.setOnClickListener {
            //val intent = Intent(this, LoginActivity::class.java)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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