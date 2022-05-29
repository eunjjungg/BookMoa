package com.smu.bookmoa.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(WriteDateConverter::class)
data class WriteDateData(
    @PrimaryKey(autoGenerate = false)
    var yearMonth: String,
    var dateList: List<Int>? = null
)
