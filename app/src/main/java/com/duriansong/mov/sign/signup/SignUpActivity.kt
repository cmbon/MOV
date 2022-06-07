package com.duriansong.mov.sign.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.duriansong.mov.R
import com.duriansong.mov.sign.signin.User
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername:String
    lateinit var sPassword:String
    lateinit var sNama:String
    lateinit var sEmail:String

    lateinit var mFirebaseIntance : FirebaseDatabase
    lateinit var mFirebaseDatabaseReference: DatabaseReference
    lateinit var mDatabase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseIntance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabaseReference = mFirebaseIntance.getReference("User")

        val btn_submit = findViewById<Button>(R.id.btn_lanjut)
        val username = findViewById<EditText>(R.id.et_signupuser)
        val password = findViewById<EditText>(R.id.et_signuppass)
        val nama = findViewById<EditText>(R.id.et_signupnama)
        val email = findViewById<EditText>(R.id.et_signupemail)

        btn_submit.setOnClickListener{
            sUsername = username.text.toString()
            sPassword = password.text.toString()
            sNama = nama.text.toString()
            sEmail = email.text.toString()

            if (sUsername.equals("")){
                username.error = "Silahkan isi username anda"
                username.requestFocus()
            } else if (sPassword.equals("")){
                password.error = "Silahkan isi password anda"
                password.requestFocus()
            } else if (sNama.equals("")){
                nama.error = "Silahkan isi nama anda"
                nama.requestFocus()
            } else if (sEmail.equals("")){
                email.error = "Silahkan isi email anda"
                email.requestFocus()
            } else {
                saveUsername (sUsername,sPassword,sNama,sEmail)
            }
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null) {
            checkUsername(sUsername, user)
        }
    }

    private fun checkUsername(sUsername: String, data: User) {
        mFirebaseDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if (user == null) { 
                   mFirebaseDatabaseReference.child(sUsername).setValue(data)

                    var gosignupphoto = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("nama",data?.nama)
                    startActivity(gosignupphoto)
                } else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }


        })
    }
}