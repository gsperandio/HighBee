package com.br.highbee.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.highbee.databinding.ActivityRegisterBinding
import com.br.highbee.view.DoneToNext
import com.br.highbee.view.SharedPref
import com.br.highbee.view.UpperFirst
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")
        val sharedPref = SharedPref(this)
        binding.register.setOnClickListener {
            usersCollection.document(binding.celphone.text.toString()).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documentSnapshot = task.result
                        if (documentSnapshot.exists()) {

                            binding.message.visibility = View.VISIBLE
                            val handler = Handler(Looper.getMainLooper())
                            handler.postDelayed({
                                startActivity(Intent(this, LoginActivity::class.java))
                            }, 2000)
                        } else {
                            val codeValidation = numCode()

                            db.collection("users").document(binding.celphone.text.toString()).set(
                                hashMapOf(
                                    "FirstName" to binding.nameUser.text.toString(),
                                    "LastName" to binding.lastnameUser.text.toString(),
                                    "Phone" to binding.celphone.text.toString(),
                                    "Code" to codeValidation
                                )
                            )

                            sharedPref.saveCache("phone", binding.celphone.text.toString())

                            startActivity(Intent(this, CodeRegister::class.java))
                        }
                    } else {
                        val exception = task.exception
                        if (exception != null) {
                            startActivity(Intent(this, WelcomePage::class.java))
                        }
                    }
                }


        }

        binding.nameUser.addTextChangedListener(UpperFirst(binding.nameUser))
        binding.lastnameUser.addTextChangedListener(UpperFirst(binding.lastnameUser))
        binding.nameUser.setOnEditorActionListener(DoneToNext(binding.nameUser, binding.lastnameUser))
        binding.lastnameUser.setOnEditorActionListener(DoneToNext(binding.lastnameUser, binding.celphone))
    }

    fun numCode(): String {
        return String.format("%06d", Random.nextInt(999, 999999))
    }
}