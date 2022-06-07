package com.duriansong.mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.duriansong.mov.checkout.CheckoutActivity
import com.duriansong.mov.databinding.ActivityPilihBangkuBinding
import com.duriansong.mov.model.Checkout
import com.duriansong.mov.model.Film

class PilihBangkuActivity : AppCompatActivity() {

    //    STATUS SEAT A
    var statusA1: Boolean = false
    var statusA2: Boolean = false
    var statusA3: Boolean = false
    var statusA4: Boolean = false

    //    STATUS SEAT B
    var statusB1: Boolean = false
    var statusB2: Boolean = false
    var statusB3: Boolean = false
    var statusB4: Boolean = false

    //    STATUS SEAT C
    var statusC1: Boolean = false
    var statusC2: Boolean = false
    var statusC3: Boolean = false
    var statusC4: Boolean = false
    //    STATUS SEAT D
    var statusD1: Boolean = false
    var statusD2: Boolean = false
    var statusD3: Boolean = false
    var statusD4: Boolean = false
    var total: Int = 0

    private var dataList = ArrayList<Checkout>()

    lateinit var binding : ActivityPilihBangkuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPilihBangkuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Film>("data")
        binding.tvBangkuJudul.text = data!!.judul

        binding.a1.setOnClickListener{
            if(statusA1){
                binding.a1.setImageResource(R.drawable.ic_rectangle_28)
                statusA1 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.a1.setImageResource(R.drawable.ic_rectangle_select)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A1", "70000")
                dataList.add(data)
            }
        }
        binding.a2.setOnClickListener{
            if(statusA2){
                binding.a2.setImageResource(R.drawable.ic_rectangle_28)
                statusA2 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.a2.setImageResource(R.drawable.ic_rectangle_select)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A2", "70000")
                dataList.add(data)
            }
        }

        binding.a3.setOnClickListener{
            if(statusA3){
                binding.a3.setImageResource(R.drawable.ic_rectangle_28)
                statusA3 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.a3.setImageResource(R.drawable.ic_rectangle_select)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "70000")
                dataList.add(data)
            }
        }
        binding.a4.setOnClickListener{
            if(statusA4){
                binding.a4.setImageResource(R.drawable.ic_rectangle_28)
                statusA4 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.a4.setImageResource(R.drawable.ic_rectangle_select)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A4", "70000")
                dataList.add(data)
            }
        }
        binding.b1.setOnClickListener{
            if(statusB1){
                binding.b1.setImageResource(R.drawable.ic_rectangle_28)
                statusB1 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.b1.setImageResource(R.drawable.ic_rectangle_select)
                statusB1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B1", "70000")
                dataList.add(data)
            }
        }
        binding.b2.setOnClickListener{
            if(statusB2){
                binding.b2.setImageResource(R.drawable.ic_rectangle_28)
                statusB2 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.b2.setImageResource(R.drawable.ic_rectangle_select)
                statusB2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B2", "70000")
                dataList.add(data)
            }
        }

        binding.b3.setOnClickListener{
            if(statusB3){
                binding.b3.setImageResource(R.drawable.ic_rectangle_28)
                statusB3 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.b3.setImageResource(R.drawable.ic_rectangle_select)
                statusB3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B3", "70000")
                dataList.add(data)
            }
        }
        binding.b4.setOnClickListener{
            if(statusB4){
                binding.b4.setImageResource(R.drawable.ic_rectangle_28)
                statusB4 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.b4.setImageResource(R.drawable.ic_rectangle_select)
                statusB4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("B4", "70000")
                dataList.add(data)
            }
        }
        binding.c1.setOnClickListener{
            if(statusC1){
                binding.c1.setImageResource(R.drawable.ic_rectangle_28)
                statusC1 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.c1.setImageResource(R.drawable.ic_rectangle_select)
                statusC1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C1", "70000")
                dataList.add(data)
            }
        }
        binding.c2.setOnClickListener{
            if(statusC2){
                binding.c2.setImageResource(R.drawable.ic_rectangle_28)
                statusC2 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.c2.setImageResource(R.drawable.ic_rectangle_select)
                statusC2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C2", "70000")
                dataList.add(data)
            }
        }
        binding.c3.setOnClickListener{
            if(statusC3){
                binding.c3.setImageResource(R.drawable.ic_rectangle_28)
                statusC3 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.c3.setImageResource(R.drawable.ic_rectangle_select)
                statusC3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C3", "70000")
                dataList.add(data)
            }
        }
        binding.c4.setOnClickListener{
            if(statusC4){
                binding.c4.setImageResource(R.drawable.ic_rectangle_28)
                statusC4 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.c4.setImageResource(R.drawable.ic_rectangle_select)
                statusC4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("C4", "70000")
                dataList.add(data)
            }
        }
        binding.d1.setOnClickListener{
            if(statusD1){
                binding.d1.setImageResource(R.drawable.ic_rectangle_28)
                statusD1 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.d1.setImageResource(R.drawable.ic_rectangle_select)
                statusD1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D1", "70000")
                dataList.add(data)
            }
        }
        binding.d2.setOnClickListener{
            if(statusD2){
                binding.d2.setImageResource(R.drawable.ic_rectangle_28)
                statusD2 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.d2.setImageResource(R.drawable.ic_rectangle_select)
                statusD2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D2", "70000")
                dataList.add(data)
            }
        }
        binding.d3.setOnClickListener{
            if(statusD3){
                binding.d3.setImageResource(R.drawable.ic_rectangle_28)
                statusD3 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.d3.setImageResource(R.drawable.ic_rectangle_select)
                statusD3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D3", "70000")
                dataList.add(data)
            }
        }
        binding.d4.setOnClickListener{
            if(statusD4){
                binding.d4.setImageResource(R.drawable.ic_rectangle_28)
                statusD4 =false
                total -= 1
                beliTiket(total)
            } else {
                binding.d4.setImageResource(R.drawable.ic_rectangle_select)
                statusD4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("D4", "70000")
                dataList.add(data)
            }
        }
        binding.btnBangkuOut.setOnClickListener{
            var intent = Intent(this@PilihBangkuActivity, CheckoutActivity::class.java ).putExtra("data",dataList)
            startActivity(intent)
        }
    }


    private fun beliTiket(total: Int) {
        if (total == 0){
            binding.btnBangkuOut.setText("Beli Tiket")
            binding.btnBangkuOut.visibility = View.INVISIBLE
        } else {
            binding.btnBangkuOut.setText("Beli Tiket ("+total+")")
            binding.btnBangkuOut.visibility = View.VISIBLE
        }
    }
}