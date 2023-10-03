package com.br.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.br.highbee.R
import com.br.highbee.databinding.ActivityLoginBinding
import com.br.highbee.view.RandomNum
import com.br.highbee.view.SharedPref
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")
        val sharedPref = SharedPref(this)

        binding.access.setOnClickListener{
            usersCollection.document(binding.celphone.text.toString()).get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    if (task.result.exists()) {
                        val updates = hashMapOf<String, Any>(
                            "code" to RandomNum.get(999,999999),
                            "status" to false
                        )

                        db.collection("users")
                            .document(binding.celphone.text.toString())
                            .update(updates)
                            .addOnSuccessListener {
                                sharedPref.saveCache("phone", binding.celphone.text.toString())
                                val intent = Intent(this, CodeRegister::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            .addOnFailureListener {
                                val intent = Intent(this, WelcomePage::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                    }else{
                        binding.message.visibility = View.VISIBLE
                        val handler = Handler(Looper.getMainLooper())
                        handler.postDelayed({
                            startActivity(Intent(this, RegisterActivity::class.java))
                        }, 2000)
                    }
                }else{
                    val exception = task.exception
                    if (exception != null) {
                        val intent = Intent(this, WelcomePage::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                }

            }
        }
    }
}