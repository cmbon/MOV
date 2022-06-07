package com.duriansong.mov.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.duriansong.mov.R
import com.duriansong.mov.sign.signin.SignInActivity
import com.duriansong.mov.utils.Preference

class OnboardingOneActivity : AppCompatActivity() {

    lateinit var preference:Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)
        val  btn_home = findViewById<Button>(R.id.btn_home)
        val  btn_daftar = findViewById<Button>(R.id.btn_daftar)

        preference = Preference(this)

        if (preference.getValues("onboarding").equals("1")){
            finishAffinity()

            var intent = Intent( this@OnboardingOneActivity, SignInActivity::class.java )
            startActivity(intent)
        }

        btn_home.setOnClickListener {
            var intent = Intent( this@OnboardingOneActivity, OnboardingTwoActivity::class.java )
            startActivity(intent)
        }
        btn_daftar.setOnClickListener {
            preference.setValues("onboarding", "1")
            finishAffinity()

            var intent = Intent( this@OnboardingOneActivity, SignInActivity::class.java )
            startActivity(intent)
        }
    }
}