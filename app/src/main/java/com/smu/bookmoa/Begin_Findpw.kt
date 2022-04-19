package com.smu.bookmoa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.smu.bookmoa.databinding.ActivityBeginFindpwBinding

class Begin_Findpw : AppCompatActivity() {

    val binding by lazy { ActivityBeginFindpwBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnFindpw.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            if(!email.isEmpty())
                finPW(email)
        }
    }

    private fun finPW(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
                val intentBeginActivity = Intent(this, BeginActivity::class.java)
                startActivity(intentBeginActivity)
            }
        }
    }
}