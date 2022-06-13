package com.smu.bookmoa

import android.content.Context

class AppLock(context: Context) {
    private var sharedPref = context.getSharedPreferences("appLock", Context.MODE_PRIVATE)

    fun setLock(password : String) {
        sharedPref.edit().apply {
            putString("applock", password)
            apply()
        }
    }

    fun removeLock() {
        sharedPref.edit().apply {
            remove("applock")
            apply()
        }
    }

    fun checkPw(password: String):Boolean {
        return sharedPref.getString("applock", "0") == password
    }

    fun isLockSet() : Boolean {
        if(sharedPref.contains("applock"))
            return true
        return false
    }
}