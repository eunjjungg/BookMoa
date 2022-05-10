package com.smu.bookmoa

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main_book_cal.*


class Main_book_cal : Fragment() {

    var pageIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_book_cal, container, false)

        val cal_custom: RecyclerView = view.findViewById(R.id.calendar_custom)
        val monthListManager = LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)
        val monthListAdapter = AdapterMonth()

        cal_custom.apply{
            layoutManager = monthListManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE / 2)
        }

        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(calendar_custom)




        return view
    }





}