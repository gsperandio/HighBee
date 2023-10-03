package com.br.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import com.br.highbee.databinding.ActivityMainBinding
import com.br.highbee.view.SharedPref
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        val fadeInAnimation = AlphaAnimation(0f, 1f)
        fadeInAnimation.duration = 1000
        fadeInAnimation.fillAfter = true

        fadeInAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        val sharedPref = SharedPref(this)
        val phoneCache: String? = sharedPref.findCache("phone")
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")

        binding.logo.startAnimation(fadeInAnimation)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            usersCollection.document(phoneCache.toString()).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documentSnapshot = task.result
                        if (documentSnapshot.exists()) {
                            val status = task.result?.getBoolean("status") ?: false
                            if(status){
                                val intent = Intent(this@MainActivity, HomePage::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                val intent = Intent(this@MainActivity, WelcomePage::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }else{
                            val intent = Intent(this@MainActivity, WelcomePage::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }

        }, 1800)
    }
}

