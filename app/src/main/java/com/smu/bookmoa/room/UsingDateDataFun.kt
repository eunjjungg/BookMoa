package com.smu.bookmoa.room

import android.content.Context
import android.util.Log
import com.smu.bookmoa.AdapterMakeService

class UsingDateDataFun {

    //writeDateData room db 안에 날짜 추가
    fun insertDate(context: Context, date: String) {
        val db = AppDatabase.getInstance(context)

        var listTmp = mutableListOf<Int>()
        var writeDateData = WriteDateData(getCurrentY_MForRoom(date), listTmp)
        db!!.writeDataDao().insertDate(writeDateData)
        val result :WriteDateData= db!!.writeDataDao().isExist(getCurrentY_MForRoom(date))

        if(result.dateList?.size == 0) {
            var listTmp = mutableListOf<Int>()
            listTmp.add(getInsertDayForRoom((date)))
            var writeDateData = WriteDateData(getCurrentY_MForRoom(date), listTmp)
            db!!.writeDataDao().updateDate(writeDateData)
        }
        else {
            var isInsertDateAlreadyExist = false
            var tempList = mutableListOf<Int>()
            for(i in result.dateList!!){
                tempList.add(i)
                if(i == getInsertDayForRoom((date))) {
                    isInsertDateAlreadyExist = true
                }
            }
            if(!isInsertDateAlreadyExist){
                tempList.add(getInsertDayForRoom((date)))
                var tempDateData = WriteDateData(getCurrentY_MForRoom(date), tempList)
                db!!.writeDataDao().updateDate(tempDateData)


            }
        }

        Log.d("writeResult", db!!.writeDataDao().getAll().toString())

    }

    fun getCurrentY_MForRoom(date: String): String{
        var dateForCloudArr = date
            .replace(" / ","_")
            .split("_")
        return dateForCloudArr[0] + "_" + dateForCloudArr[1]
    }

    fun getInsertDayForRoom(date: String): Int{
        var dateForCloudArr = date
            .replace(" / ","_")
            .split("_")
        return dateForCloudArr[2].toInt()
    }

}