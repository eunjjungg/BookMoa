package com.smu.bookmoa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smu.bookmoa.databinding.FragmentMainSettingBinding

class Main_setting : Fragment() {

    val binding by lazy {FragmentMainSettingBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvLock.setOnClickListener {
            val lockIntent = Intent(activity, Main_set_lock::class.java)
            startActivity(lockIntent)
        }
        binding.tvAlarm.setOnClickListener {
            val alarmIntent = Intent(activity, Main_set_alarm::class.java)
            startActivity(alarmIntent)
        }
        binding.tvAcc.setOnClickListener {
            val accIntent = Intent(activity, Main_set_acc::class.java)
            startActivity(accIntent)
        }
        binding.tvStat.setOnClickListener {
            val statIntent = Intent(activity, Main_set_stat::class.java)
            startActivity(statIntent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_main_setting, container, false)
        return binding.root
    }
}