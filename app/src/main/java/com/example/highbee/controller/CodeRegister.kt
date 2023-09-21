package com.example.highbee.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.highbee.R
import com.example.highbee.databinding.ActivityCodeRegisterBinding

class CodeRegister : AppCompatActivity() {
    private lateinit var binding: ActivityCodeRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resend.setOnClickListener {
            val intent = Intent(this, TermsOfUse::class.java)
            startActivity(intent)
        }

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

        binding.numberOne.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length == 1){
                    binding.numberTwo.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

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
                    val intent = Intent(this@CodeRegister, TermsOfUse::class.java)
                    startActivity(intent)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }
}