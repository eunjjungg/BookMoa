package com.smu.bookmoa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smu.bookmoa.databinding.ActivitySetStatBinding

class Main_set_stat : AppCompatActivity() {

    val binding by lazy { ActivitySetStatBinding.inflate(layoutInflater) }

    //cloud firestore 용 변수
    var fbAuth : FirebaseAuth? = null
    var fbFirestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.readUntilNow.text =
            FirestoreCount().getAmountReadUntilNow(binding.root.context)
        binding.readMonth.text = FirestoreCount().getAmoutReadMonth(binding.root.context)
        binding.readWeek.text = FirestoreCount().getAmoutReadWeek(binding.root.context)
        //binding.readPlatform.setImageResource()
    }


}