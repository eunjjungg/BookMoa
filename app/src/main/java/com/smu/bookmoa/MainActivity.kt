package com.smu.bookmoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smu.bookmoa.databinding.ActivityBeginBinding
import com.smu.bookmoa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val selectionBook: Int = 0
    val selectionEvent: Int = 1
    val selectionSetting: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.mainFrame.id,Main_book_cal())
        transaction.commit()
        activeIcon(selectionBook)

        binding.btnBook.setOnClickListener(this)
        binding.btnEvent.setOnClickListener(this)
        binding.btnSetting.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val transaction = supportFragmentManager.beginTransaction()

        when(p0?.id) {
            binding.btnBook.id -> {
                transaction.replace(binding.mainFrame.id, Main_book_cal())
                transaction.commit()
                activeIcon(selectionBook)
            }
            binding.btnEvent.id -> {
                transaction.replace(binding.mainFrame.id, Main_event_cal())
                transaction.commit()
                activeIcon(selectionEvent)
            }
            binding.btnSetting.id -> {
                transaction.replace(binding.mainFrame.id, Main_setting())
                transaction.commit()
                activeIcon(selectionSetting)
            }
        }
    }

    fun activeIcon(selection: Int){
        binding.btnBook.setImageResource(R.drawable.ic_book_unselected)
        binding.btnEvent.setImageResource(R.drawable.ic_event_unselected)
        binding.btnSetting.setImageResource(R.drawable.ic_mypage_unselected)

        when(selection) {
            selectionBook -> binding.btnBook.setImageResource(R.drawable.ic_book_selected)
            selectionEvent -> binding.btnEvent.setImageResource(R.drawable.ic_event_selected)
            selectionSetting -> binding.btnSetting.setImageResource(R.drawable.ic_mypage_selected)
        }
    }

}