package com.br.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.br.highbee.R
import com.br.highbee.databinding.ActivityCodeRegisterBinding
import com.br.highbee.view.SharedPref
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class CodeRegister : AppCompatActivity() {
    private lateinit var binding: ActivityCodeRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeRegisterBinding.inflate(layoutInflater)
        val totalTime = 30
        val countDown = 1000
        setContentView(binding.root)
        val sharedPref = SharedPref(this)
        val phoneCache: String? = sharedPref.findCache("phone")
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")

        binding.returningButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.numberOne.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 1){
                    binding.numberTwo.requestFocus()
                }
            }
        })

        binding.numberTwo.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 1){
                    binding.numberThree.requestFocus()
                }else if(p0?.length == 0){
                    binding.numberOne.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.numberThree.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 1){
                    binding.numberFour.requestFocus()
                }else if(p0?.length == 0){
                    binding.numberTwo.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.numberFour.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 1){
                    binding.numberFive.requestFocus()
                }else if(p0?.length == 0){
                    binding.numberThree.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.numberFive.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 1){
                    binding.numberSix.requestFocus()
                }else if(p0?.length == 0){
                    binding.numberFour.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.numberSix.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0?.length == 0){
                    binding.numberFive.requestFocus()
                }else{
                    verifyCode(usersCollection, phoneCache)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        val countDownTimer = object : CountDownTimer(totalTime * 1000L, countDown.toLong()){
            override fun onTick(p0: Long) {
                val seconds = (p0 / 1000).toInt()
                binding.secondsToRefresh.text = "Aguarde $seconds segundos para Reenviar o código"
            }

            override fun onFinish() {
                binding.secondsToRefresh.visibility = View.GONE
                binding.resend.visibility = View.VISIBLE
            }

        }

        countDownTimer.start()

        binding.resend.setOnClickListener {
//            val intent = Intent(this, TermsOfUse::class.java)
//            startActivity(intent)

            binding.resend.visibility = View.GONE
            binding.secondsToRefresh.visibility = View.VISIBLE
            countDownTimer.start()
        }
    }


    fun verifyCode(collection : CollectionReference, phone: String?){
        collection.document(phone.toString()).get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    if(task.result.exists()){


                        if(task.result.getString("code") == betweenUs())
                            startActivity(Intent(this@CodeRegister, TermsOfUse::class.java))
                    }
                }
            }


    }

    private fun betweenUs(): String{
        return binding.numberOne.text.toString() +
                binding.numberTwo.text.toString() +
                binding.numberThree.text.toString() +
                binding.numberFour.text.toString() +
                binding.numberFive.text.toString() +
                binding.numberSix.text.toString()
    }

}