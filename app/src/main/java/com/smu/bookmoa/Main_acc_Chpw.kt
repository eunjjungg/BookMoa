package com.smu.bookmoa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.smu.bookmoa.databinding.ActivityAccChPwBinding

class Main_acc_Chpw : AppCompatActivity() {

    val binding by lazy { ActivityAccChPwBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            findPassword()
        }


    }


    fun findPassword(){
        FirebaseAuth.getInstance().sendPasswordResetEmail(binding.etEmail.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this, "비밀번호 변경 메일을 전송했습니다", Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}