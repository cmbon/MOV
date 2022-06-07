package com.duriansong.mov.home.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duriansong.mov.DetailActivity
import com.duriansong.mov.databinding.FragmentDashboardBinding
import com.duriansong.mov.model.Film
import com.duriansong.mov.utils.Preference
import com.google.firebase.database.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


private var _binding: FragmentDashboardBinding? = null
private val binding get() = _binding!!

class DashboardFragment : Fragment() {

    private lateinit var preference: Preference
    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preference(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance("https://mov-database-4e1e6-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Film")

        binding.tvNamaUser.setText(preference.getValues("nama"))
        if (!preference.getValues("saldo").equals("")){
            currency(preference.getValues("saldo")!!.toDouble(), binding.tvAngka)
        }

        Glide.with(this)
            .load(preference.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivWajah)

        binding.rvNowplaying.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvComingsoon.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Film::class.java!!)
                    dataList.add(film!!)
                }

                binding.rvNowplaying.adapter = NowPlayingAdaptor(dataList){
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                binding.rvComingsoon.adapter = ComingSoonAdaptor(dataList){
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, ""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }

}