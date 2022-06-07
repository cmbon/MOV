package com.duriansong.mov.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.duriansong.mov.home.HomeActivity
import com.duriansong.mov.R
import com.duriansong.mov.R.id.et_username
import com.duriansong.mov.sign.signup.SignUpActivity
import com.duriansong.mov.utils.Preference
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String

    lateinit var mDatabase: DatabaseReference
    lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preference(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        val btn = findViewById<Button>(R.id.et_signup)
        val btn_daftar = findViewById<Button>(R.id.et_skip)
        val user_et = findViewById<EditText>(et_username)
        val password_et = findViewById<EditText>(R.id.et_password)
        btn.setOnClickListener{
           iUsername = user_et.text.toString()
            iPassword = password_et.text.toString()

            if(iUsername.equals("")){
                user_et.error = "Silahkan tulis username anda"
                user_et.requestFocus()
            }   else if(iPassword.equals("")){
                password_et.error = "Silahkan tulis password anda"
                password_et.requestFocus()
            }   else {
                pushLogin(iUsername, iPassword)
            }
        }
        btn_daftar.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String){
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()

            }


            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if(user == null){
                    Toast.makeText(this@SignInActivity, "Username tidak ditemukan", Toast.LENGTH_LONG).show()
                } else {

                    if(user.password.equals(iPassword)){

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("user", user.username.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password anda salah", Toast.LENGTH_LONG).show()
                    }

                }
            }


        })
    }
}