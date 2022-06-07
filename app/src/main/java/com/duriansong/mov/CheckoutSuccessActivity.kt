package com.duriansong.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duriansong.mov.databinding.ActivityCheckoutSuccessBinding
import com.duriansong.mov.home.HomeActivity

class CheckoutSuccessActivity : AppCompatActivity() {
    lateinit var binding : ActivityCheckoutSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckoutSuccessBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)
        setContentView(binding.root)

        binding.btnSuccesBack.setOnClickListener{
            finishAffinity()

            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}