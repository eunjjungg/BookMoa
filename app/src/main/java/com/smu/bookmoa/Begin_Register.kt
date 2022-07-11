package com.smu.bookmoa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.smu.bookmoa.databinding.ActivityBeginRegisterBinding

class Begin_Register : AppCompatActivity() {

    val binding by lazy { ActivityBeginRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val pw = binding.txtPsw.text.toString()
            val pwCh = binding.pwCheck.text.toString()
            if(!email.isEmpty() && !pw.isEmpty() && !pwCh.isEmpty()) {
                if(pw.equals(pwCh))
                    doRegister(email, pw)
            }
        }
    }

    private fun doRegister(email: String, pw: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pw).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                val intentBeginActivity = Intent(this, BeginActivity::class.java)
                startActivity(intentBeginActivity)
            }
        }
    }
}