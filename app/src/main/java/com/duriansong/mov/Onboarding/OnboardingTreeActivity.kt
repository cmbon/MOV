package com.duriansong.mov.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.duriansong.mov.R
import com.duriansong.mov.sign.signin.SignInActivity

class OnboardingTreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_tree)
        val btn_home = findViewById<Button>(R.id.btn_next2)
        btn_home.setOnClickListener{
            finishAffinity()
            val intent = Intent( this@OnboardingTreeActivity, SignInActivity::class.java )
            startActivity(intent)
        }

    }
}