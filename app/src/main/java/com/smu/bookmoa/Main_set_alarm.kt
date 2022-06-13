package com.smu.bookmoa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.bookmoa.databinding.ActivityMainSetAlarmBinding

class Main_set_alarm : AppCompatActivity() {

    val binding by lazy { ActivityMainSetAlarmBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}