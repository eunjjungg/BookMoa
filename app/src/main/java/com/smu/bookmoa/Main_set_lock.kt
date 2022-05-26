package com.smu.bookmoa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.bookmoa.databinding.ActivitySetLockBinding

class Main_set_lock : AppCompatActivity() {

    val binding by lazy {ActivitySetLockBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}