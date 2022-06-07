package com.duriansong.mov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.duriansong.mov.CheckoutSuccessActivity
import com.duriansong.mov.databinding.ActivityCheckoutBinding
import com.duriansong.mov.model.Checkout
import com.duriansong.mov.utils.Preference

class CheckoutActivity : AppCompatActivity() {


    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preference: Preference
    lateinit var binding : ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preference = Preference(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total harus dibayarkan", total.toString()))

        binding.rcCheckout.layoutManager = LinearLayoutManager(this)
        binding.rcCheckout.adapter = CheckoutAdaptor(dataList) {

        }
        binding.btnCheckoutBayar.setOnClickListener{
            val intent = Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }
        binding.btnBayarOut.setOnClickListener{
            finish()
        }
    }
}