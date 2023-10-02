package com.br.highbee.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.highbee.databinding.ActivityRegisterBinding
import com.br.highbee.model.User
import com.br.highbee.view.DoneToNext
import com.br.highbee.view.SharedPref
import com.br.highbee.view.UpperFirst
import com.google.firebase.firestore.FirebaseFirestore
import com.br.highbee.view.RandomNum

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
                                val intent = Intent(this, LoginActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }, 2000)
                        } else {
                            val user = User(
                                Code = RandomNum.get(999, 999999),
                                FirstName = binding.nameUser.text.toString(),
                                LastName = binding.lastnameUser.text.toString(),
                                Phone = binding.celphone.text.toString()
                            )

                            db.collection("users").document(binding.celphone.text.toString()).set(
                                user
                            )

                            sharedPref.saveCache("phone", binding.celphone.text.toString())

                            val intent = Intent(this, CodeRegister::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    } else {
                        val exception = task.exception
                        if (exception != null) {
                            val intent = Intent(this, WelcomePage::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                    }
                }


        }

        binding.nameUser.addTextChangedListener(UpperFirst(binding.nameUser))
        binding.lastnameUser.addTextChangedListener(UpperFirst(binding.lastnameUser))
        binding.nameUser.setOnEditorActionListener(DoneToNext(binding.nameUser, binding.lastnameUser))
        binding.lastnameUser.setOnEditorActionListener(DoneToNext(binding.lastnameUser, binding.celphone))
    }
}