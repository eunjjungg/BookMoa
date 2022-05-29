package com.smu.bookmoa

import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.list_item_month.view.*
import java.util.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


class AdapterMakeService {

    private var calendar = Calendar.getInstance()

    fun makeMonth(inputDate: String): AdapterDay {
        val dateTemp = inputDate.replace(" / ","-")
        val dateTempArr = dateTemp.split("-")
        val yyyy = "20" + dateTempArr[0]
        val mm: String

        if(dateTempArr[1].toInt() < 10)
            mm = "0" + dateTempArr[1]
        else
            mm = dateTempArr[1]

        val dd: String
            if(dateTempArr[2].toInt() < 10)
                dd = "0" + dateTempArr[2]
            else dd = dateTempArr[2]

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(yyyy+"-"+mm+"-"+dd, pattern)

        calendar.set(date.year, date.monthValue, date.dayOfMonth)

        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val tempMonth = calendar.get(Calendar.MONTH)

        var dayList: MutableList<Date> = MutableList(6*7) { Date() }
        for(i in 0..5){
            for(k in 0..6){
                calendar.add(Calendar.DAY_OF_MONTH, (1 - calendar.get(Calendar.DAY_OF_WEEK)) + k)
                dayList[i * 7 + k] = calendar.time
            }
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }

        return AdapterDay(tempMonth, dayList)
    }

}