package com.duriansong.mov.home.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.duriansong.mov.databinding.FragmentSettingBinding
import com.duriansong.mov.utils.Preference


private var _binding: FragmentSettingBinding? = null
private val binding get() = _binding!!


class settingFragment : Fragment() {

    lateinit var preference: Preference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSettingBinding.inflate(inflater,container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preference(requireContext())

        binding.tvNamaSetting.text = preference.getValues("nama")
        binding.tvEmailSetting.text =  preference.getValues("email")

        Glide.with(this)
            .load(preference.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfileSetting)
    }




}