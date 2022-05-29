package com.smu.bookmoa.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WriteDateDao {
    @Query("SELECT * FROM WriteDateData")
    fun getAll(): List<WriteDateData>

    @Query("SELECT * FROM WriteDateData WHERE yearMonth IN (:date)")
    fun isExist(date: String): WriteDateData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDate(writeDateData: WriteDateData)

    @Update
    fun updateDate(writeDateData: WriteDateData)
}
