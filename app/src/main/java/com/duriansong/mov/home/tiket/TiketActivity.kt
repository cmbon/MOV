package com.duriansong.mov.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.duriansong.mov.R
import com.duriansong.mov.databinding.ActivityCheckoutBinding
import com.duriansong.mov.databinding.ActivityTiketBinding
import com.duriansong.mov.model.Checkout
import com.duriansong.mov.model.Film

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    lateinit var binding: ActivityTiketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTiketBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var data = intent.getParcelableExtra<Film>("data")


        binding.tvTitle.text = data?.judul
        binding.tvDirec.text = data?.genre
        binding.tvRating.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(binding.ivPosterImage)

        binding.rcCheckout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1", ""))
        dataList.add(Checkout("C2", ""))

        binding.rcCheckout.adapter = TiketAdaptor(dataList){

        }
    }
}