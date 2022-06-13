package com.smu.bookmoa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smu.bookmoa.databinding.ActivitySetAccBinding

class Main_set_acc : AppCompatActivity() {

    val binding by lazy { ActivitySetAccBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.tvChpw.setOnClickListener {
            val intent = Intent(this, Main_acc_Chpw::class.java)
            startActivity(intent)
        }

        var email: String? = ""

        val user = Firebase.auth.currentUser
        user?.let {
            email = user.email
        }
        binding.txtEmail.text = "이메일 : ${email}"
    }
}