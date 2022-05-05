package com.smu.bookmoa


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_book_cal.*
import java.util.*


class Main_book_cal : Fragment() {

    private lateinit var calendarAdapter: CalendarAdapter

    var pageIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_main_book_cal, container, false)
        return view
    }





}