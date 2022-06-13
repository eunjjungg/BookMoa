package com.smu.bookmoa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.smu.bookmoa.databinding.ActivityMainEventDetBinding
import java.text.SimpleDateFormat

class Main_event_det : AppCompatActivity() {

    val binding by lazy { ActivityMainEventDetBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentTime : Long = System.currentTimeMillis()
        val dataFormat = SimpleDateFormat("MM/dd E")
        val currentDate = dataFormat.format(currentTime)
        binding.pageName.text = currentDate
        binding.tvEvent.text = intent.getStringExtra("name")
        binding.tvPlatform.text = intent.getStringExtra("platform")
        binding.tvPeriod.text = intent.getStringExtra("period")
        Glide.with(this)
                .load(intent.getStringExtra("img"))
                .into(binding.eventImg)


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnLink.setOnClickListener {
            val eventIntent = Intent(Intent.ACTION_VIEW, Uri.parse(intent.getStringExtra("eventUrl")))
            startActivity(eventIntent)
        }
    }
}