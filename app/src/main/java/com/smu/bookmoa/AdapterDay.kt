package com.smu.bookmoa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.smu.bookmoa.room.AppDatabase
import com.smu.bookmoa.room.WriteDateData
import kotlinx.android.synthetic.main.list_item_day.view.*
import java.util.*


class AdapterDay(val tempMonth:Int, val dayList:MutableList<Date>): RecyclerView.Adapter<AdapterDay.DayView>() {
    val ROW = 6
    lateinit var parentContext: Context

    inner class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        parentContext = parent.context

        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        //날짜 클릭 시 intent로 book write 부르기
        holder.layout.item_day_layout.setOnClickListener {
            if(tempMonth == dayList[position].month){
                val intent = Intent(holder.itemView?.context, BookWrite::class.java)
                //year / month / date 형식으로 날짜 String 전달
                //ex: 22 / 5 / 12 , 21 / 12 / 3
                intent.putExtra("date",
                    "${(dayList[position].year.toInt() - 100)} / ${(dayList[position].month.toInt() + 1)} / ${dayList[position].date}")
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        }

        //날짜 그리기
        holder.layout.item_day_text.text = dayList[position].date.toString()

        //주말의 경우 색 수정
        holder.layout.item_day_text.setTextColor(when(position % 7){
            0 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        })

        //보고 있는 달의 데이터만 반영하여 뜸
        //room db: write date data의 내용에 따라 해당 날짜에 기록이 있으면 check 사진 뜨도록
        if(tempMonth == dayList[position].month){
            val db = AppDatabase.getInstance(holder.itemView.context)
            var listTmp = mutableListOf<Int>()
            var writeDateData = WriteDateData(
                "${(dayList[position].year - 100)} / ${(dayList[position].month + 1)} / ${dayList[position].date}"
                , listTmp)
            db!!.writeDataDao().insertDate(writeDateData)
            val existDataDateList = db!!.writeDataDao()
                ?.isExist("${(dayList[position].year - 100)}_${(dayList[position].month + 1)}")
                ?.dateList
            if(!existDataDateList.isNullOrEmpty()) {
                for(i in existDataDateList!!){
                    when(dayList[position].date) {
                        i -> holder.layout.checking.setImageResource(R.drawable.ic_book_selected)
                    }
                }
            }

        }

        //보고 있는 달의 날짜가 아닌 경우 투명도 낮추기
        if(tempMonth != dayList[position].month){
            holder.layout.item_day_text.alpha = 0.4f
        }

    }

    override fun getItemCount(): Int {
        return ROW * 7
    }





}