package com.duriansong.mov.sign.signup

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duriansong.mov.home.HomeActivity
import com.duriansong.mov.R
import com.duriansong.mov.utils.Preference
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.*

class SignUpPhotoScreenActivity : AppCompatActivity(), PermissionListener {

    val get_img = 1
    var statusAdd:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageRef : StorageReference
    lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)

        val txt_wel = findViewById<TextView>(R.id.tv_welcome)
        val iv_add = findViewById<ImageView>(R.id.iv_add)
        val btn_sv = findViewById<Button>(R.id.btn_svProfile)
        val iv_profile = findViewById<ImageView>(R.id.iv_profile)
        val btn_skip = findViewById<Button>(R.id.btn_nextProfile)

        preference = Preference( this)
        storage = FirebaseStorage.getInstance()
        storageRef = storage.getReference()

        txt_wel.text = "Selamat Datang\n"+intent.getStringExtra("nama")

        iv_add.setOnClickListener{
            if(statusAdd) {
                statusAdd = false
                btn_sv.visibility = View.VISIBLE
                iv_add.setImageResource(R.drawable.ic_baseline_arrow_circle_up_24)
                iv_profile.setImageResource(R.drawable.user_pic)
            } else {
                Dexter.withActivity(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()
            }
        }

        btn_skip.setOnClickListener{
            finishAffinity()

            var gohome = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
            startActivity(gohome)
        }

        btn_sv.setOnClickListener{
            if (filePath != null) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageRef.child("image/"+UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this,"Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preference.setValues("url", it.toString())
                        }

                        finishAffinity()
                        var gohome = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
                        startActivity(gohome)
                    }
                    .addOnFailureListener{
                        progressDialog.dismiss()
                        Toast.makeText(this, "Fieled", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload "+progress.toInt()+ "%")
                    }
            } else {
                var gohome = Intent(this@SignUpPhotoScreenActivity, SignUpPhotoScreenActivity::class.java)
                startActivity(gohome)
            }
        }
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
       Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
           takePictureIntent ->
           takePictureIntent.resolveActivity(packageManager)?.also {
               startActivityForResult(takePictureIntent,get_img)
           }
       }
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        TODO("Not yet implement")
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak dapat menambah foto profile", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Tergesah? klik tombol upload nanti aja!", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == get_img && resultCode == Activity.RESULT_OK){
            var bitmap = data?.extras?.get("data") as Bitmap
            statusAdd = true
            val iv_profile = findViewById<ImageView>(R.id.iv_profile)
            val btn_sv = findViewById<Button>(R.id.btn_svProfile)
            val iv_add = findViewById<ImageView>(R.id.iv_add)

            filePath = data.getData()!!
            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

            btn_sv.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_btn_delete)
        }
    }
}