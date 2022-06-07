package com.duriansong.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.duriansong.mov.databinding.ActivityDetailBinding
import com.duriansong.mov.home.Dashboard.PlaysAdapter
import com.duriansong.mov.model.Film
import com.duriansong.mov.model.Plays
import com.google.firebase.database.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

        binding.tvDetailView.text = data?.judul
        binding.tvDetailGenre.text = data?.genre
        binding.tvDetailDesc.text = data?.desc
        binding.tvDetailRate.text = data?.judul

        Glide.with(this)
            .load(data?.poster)
            .into(binding.ivDetailPoster)

        binding.rvWhoPlay.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        binding.btnDetailPilih.setOnClickListener{
            var intent = Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data",data)
            startActivity(intent)
        }
    }

    private fun getData(){
        mDatabase.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
              Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()

            }

            override fun onDataChange(snapshot: DataSnapshot) {
               dataList.clear()

                for(getdataSnapshot in snapshot.children){
                    var Film = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(Film!!)
                }

                binding.rvWhoPlay.adapter = PlaysAdapter(dataList){

                }
            }
        }
        )
    }
}