package com.smu.bookmoa

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smu.bookmoa.room.AppDatabase
import com.smu.bookmoa.room.WriteDateData
import kotlinx.android.synthetic.main.list_item_day.view.*
import java.text.SimpleDateFormat
import java.util.*

class FirestoreCount {

    fun getAmountReadUntilNow(context: Context): String{
        var sum = 0
        val db = AppDatabase.getInstance(context)
        val existDateM_Y = db!!.writeDataDao().getAll()

        for (i in existDateM_Y){
            sum += i.dateList!!.size
        }

        return sum.toString()
    }

    fun getAmoutReadMonth(context: Context): String {
        var sum = 0
        val db = AppDatabase.getInstance(context)
        val formatter = SimpleDateFormat("yy_M", Locale.getDefault())
        val currentM_Y = formatter.format(Calendar.getInstance().time)


        var listTmp = mutableListOf<Int>()
        var writeDateData = WriteDateData("$currentM_Y", listTmp)
        db!!.writeDataDao().insertDate(writeDateData)
        val existDataDateList = db!!.writeDataDao()
            ?.isExist("$currentM_Y")
            ?.dateList
        if(!existDataDateList.isNullOrEmpty()) {
            sum = existDataDateList.size
        }


        return sum.toString()
    }

    fun getAmoutReadWeek(context: Context): String {
        var sum = 0
        val db = AppDatabase.getInstance(context)
        val formatter = SimpleDateFormat("yy_M", Locale.getDefault())
        val currentM_Y = formatter.format(Calendar.getInstance().time)

        val formatterDay = SimpleDateFormat("dd", Locale.getDefault())
        val currentdd = formatterDay.format(Calendar.getInstance().time)


        var listTmp = mutableListOf<Int>()
        var writeDateData = WriteDateData("$currentM_Y", listTmp)
        db!!.writeDataDao().insertDate(writeDateData)
        val existDataDateList = db!!.writeDataDao()
            ?.isExist("$currentM_Y")
            ?.dateList
        for(i in existDataDateList!!){
            if(i > currentdd.toInt() - 7 && i < currentdd.toInt() + 1)
                sum++
        }


        return sum.toString()
    }
}