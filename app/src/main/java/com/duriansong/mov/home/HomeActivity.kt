package com.duriansong.mov.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.duriansong.mov.R
import com.duriansong.mov.databinding.ActivityHomeBinding
import com.duriansong.mov.home.Dashboard.DashboardFragment
import com.duriansong.mov.home.setting.settingFragment
import com.duriansong.mov.home.tiket.tiketFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val fragmentHome = DashboardFragment()
        val fragmentTiket = tiketFragment()
        val fragmentSetting = settingFragment()

        setFragment(fragmentHome)

        binding.menu1.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(binding.menu1, R.drawable.ic_home_active)
            changeIcon(binding.menu2, R.drawable.ic_tiket_20)
            changeIcon(binding.menu3, R.drawable.ic_profile_21)
        }
        binding.menu2.setOnClickListener{
            setFragment(fragmentTiket)

            changeIcon(binding.menu1, R.drawable.ic_home)
            changeIcon(binding.menu2, R.drawable.ic_tiket_active)
            changeIcon(binding.menu3, R.drawable.ic_profile_21)
        }
        binding.menu3.setOnClickListener{
            setFragment(fragmentSetting)

            changeIcon(binding.menu1, R.drawable.ic_home)
            changeIcon(binding.menu2, R.drawable.ic_tiket_20)
            changeIcon(binding.menu3, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}