package com.smu.bookmoa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_month.view.*
import java.util.*

class AdapterMonth(): RecyclerView.Adapter<AdapterMonth.MonthView>() {

    var pvt = 0
    val max = Int.MAX_VALUE
    val max2 = 1
    val center = max2 / 2
    private var calendar = Calendar.getInstance()

    inner class MonthView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMonth.MonthView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_month, parent, false)
        return MonthView(view)
    }

    override fun onBindViewHolder(holder: AdapterMonth.MonthView, position: Int) {
        makeMonth(holder, position, pvt)

        // next month 수정
        holder.layout.btnNextMonth.setOnClickListener {
            pvt++
            makeMonth(holder, position, pvt)
        }

        // prev month
        holder.layout.btnPrevMonth.setOnClickListener {
            pvt--
            makeMonth(holder, position, pvt)
        }
    }

    override fun getItemCount(): Int {
        return max2
    }

    fun makeMonth(holder: AdapterMonth.MonthView, position: Int, pvt: Int) {
        calendar.time = Date()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.add(Calendar.MONTH, position + pvt - center)
        holder.layout.item_month_text.text = "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH) + 1}월"
        val tempMonth = calendar.get(Calendar.MONTH)

        var dayList: MutableList<Date> = MutableList(6*7) {Date()}
        for(i in 0..5){
            for(k in 0..6){
                calendar.add(Calendar.DAY_OF_MONTH, (1 - calendar.get(Calendar.DAY_OF_WEEK)) + k)
                dayList[i * 7 + k] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }

        val dayListManager = GridLayoutManager(holder.layout.context, 7)
        val dayListAdapter = AdapterDay(tempMonth, dayList)

        holder.layout.item_month_day_list.apply {
            layoutManager = dayListManager
            adapter = dayListAdapter
        }
    }

}