package com.br.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.br.highbee.R
import com.br.highbee.databinding.ActivityTermsOfUseBinding
import com.br.highbee.view.SharedPref
import com.google.firebase.firestore.FirebaseFirestore

class TermsOfUse : AppCompatActivity() {
    private lateinit var binding: ActivityTermsOfUseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsOfUseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPref = SharedPref(this)
        val phoneCache: String? = sharedPref.findCache("phone")
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")
        val updates = hashMapOf<String, Any?>(
            "status" to true
        )

        binding.haveAccess.setOnClickListener {
            if(binding.meuCheckBox.isChecked){
                db.collection("users")
                    .document(phoneCache.toString())
                    .update(updates)
                    .addOnSuccessListener {
                        val intent = Intent(this, HomePage::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        val intent = Intent(this, WelcomePage::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
            }else{
                showToast()
            }
        }
    }

    private fun showToast(message: String = "Aceita os termos para continuar") {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}