package com.smu.bookmoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smu.bookmoa.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_main_book_cal.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.mainFrame.id,Main_book_cal())
        transaction.commit()

        binding.btnBook.setOnClickListener(this)
        binding.btnEvent.setOnClickListener(this)
        binding.btnSetting.setOnClickListener(this)

        calendarAdapter = CalendarAdapter(this)

        calendar.adapter = calendarAdapter
        calendar.setCurrentItem(CalendarAdapter.START_POSITION, false)


    }

    override fun onClick(p0: View?) {
        val transaction = supportFragmentManager.beginTransaction()

        when(p0?.id) {
            binding.btnBook.id -> {
                transaction.replace(binding.mainFrame.id, Main_book_cal())
                transaction.commit()
            }
            binding.btnEvent.id -> {
                transaction.replace(binding.mainFrame.id, Main_event_cal())
                transaction.commit()
            }
            binding.btnSetting.id -> {
                transaction.replace(binding.mainFrame.id, Main_setting())
                transaction.commit()
            }
        }
    }

}