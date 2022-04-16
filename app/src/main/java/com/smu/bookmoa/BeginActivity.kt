package com.smu.bookmoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class BeginActivity : AppCompatActivity(), View.OnClickListener {

    val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin)

        findViewById<Button>(R.id.btn_findpw).setOnClickListener(this)
        findViewById<Button>(R.id.btn_register).setOnClickListener(this)
        findViewById<Button>(R.id.btn_login).setOnClickListener(this)

    }



    override fun onClick(p0: View?) {


        when(p0?.id){
            R.id.btn_findpw -> {
                transaction.add(R.id.framelayout_begin, Begin_Findpw())
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.btn_register -> {
                transaction.replace(R.id.framelayout_begin, Begin_Register())
                transaction.addToBackStack(null)
                transaction.commit()
            }
            R.id.btn_login -> {
                transaction.replace(R.id.framelayout_begin, Begin_Login())
                transaction.hide(Begin_Home())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
}