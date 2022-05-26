package com.smu.bookmoa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.bookmoa.databinding.ActivityAccChPwBinding

class Main_acc_Chpw : AppCompatActivity() {

    val binding by lazy { ActivityAccChPwBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}