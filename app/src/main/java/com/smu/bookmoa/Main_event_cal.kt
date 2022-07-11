package com.smu.bookmoa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smu.bookmoa.databinding.FragmentMainEventCalBinding
import java.text.SimpleDateFormat

class Main_event_cal : Fragment() {

    val binding by lazy { FragmentMainEventCalBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentTime : Long = System.currentTimeMillis()
        val dataFormat = SimpleDateFormat("yy MM dd E")
        val currentDate = dataFormat.format(currentTime)
        binding.tvDate.setText(currentDate)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_event_cal, container, false)
        return binding.root
    }


}