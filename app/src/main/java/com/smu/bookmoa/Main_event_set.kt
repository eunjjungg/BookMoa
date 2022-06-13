package com.smu.bookmoa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.bookmoa.databinding.ActivityMainEventSetBinding

class Main_event_set : AppCompatActivity() {
    val binding by lazy {ActivityMainEventSetBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnSet.setOnClickListener {
            val intent = Intent()
            var list = booleanArrayOf(binding.chRidi.isChecked, binding.chYes.isChecked, binding.chAla.isChecked)
            intent.putExtra("check", list)
            if(list.get(0) == false && list.get(1) == false && list.get(2) == false)
                setResult((RESULT_OK))
            else
                setResult(RESULT_OK, intent)
            finish()
        }
    }
}