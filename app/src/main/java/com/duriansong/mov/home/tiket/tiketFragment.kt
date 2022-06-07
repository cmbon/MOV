package com.duriansong.mov.home.tiket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.duriansong.mov.R
import com.duriansong.mov.databinding.FragmentTiketBinding
import com.duriansong.mov.home.Dashboard.ComingSoonAdaptor
import com.duriansong.mov.model.Film
import com.duriansong.mov.utils.Preference
import com.google.firebase.database.*



class tiketFragment : Fragment() {

    private var _binding:FragmentTiketBinding? = null
    private val binding get() = _binding!!

    private lateinit var preference: Preference
    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentTiketBinding.inflate(inflater,container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preference(requireContext())
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        binding.rcTiket.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               dataList.clear()
                for(datashot in snapshot.children){
                    val film= datashot.getValue(Film::class.java)
                    dataList.add(film!!)
                }
                binding.rcTiket.adapter = ComingSoonAdaptor(dataList){
                    var intent = Intent(context, TiketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
                binding.tvTotal.setText(dataList.size.toString() +" Movies")
            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(context, " "+error.message,Toast.LENGTH_LONG).show()
            }

        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}