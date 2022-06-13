package com.smu.bookmoa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smu.bookmoa.databinding.ActivitySetLockBinding

class Main_set_lock : AppCompatActivity() {

    val binding by lazy {ActivitySetLockBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.switchLock.setOnClickListener {
            if (binding.switchLock.isChecked)
                binding.setPW.isEnabled = true
        }

        binding.setPW.setOnClickListener {
                val intent = Intent(this, Main_set_lock_pw::class.java)
                startActivity(intent)
        }
    }

//    override fun onRestart() {
//        super.onRestart()
//        if(AppLock(this).isLockSet()){
//            val intent = Intent(this, Main_set_lock_pw::class.java).apply {
//                putExtra(AppLockConst.type, AppLockConst.UNLOCK_PASSWORD)
//            }
//            startActivity(intent)
//        }
//    }
}