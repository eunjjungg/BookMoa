package com.smu.bookmoa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.bookmoa.databinding.ActivitySetStatBinding

class Main_set_stat : AppCompatActivity() {

    val binding by lazy { ActivitySetStatBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}