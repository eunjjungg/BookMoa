package com.smu.bookmoa.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WriteDateData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun writeDataDao(): WriteDateDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-contacts"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}