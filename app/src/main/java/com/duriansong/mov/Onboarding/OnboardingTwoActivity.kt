package com.duriansong.mov.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.duriansong.mov.R

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)
        val btn_home = findViewById<Button>(R.id.btn_next)

        btn_home.setOnClickListener {
            var intent = Intent( this@OnboardingTwoActivity, OnboardingTreeActivity::class.java )
            startActivity(intent)
        }
    }
}