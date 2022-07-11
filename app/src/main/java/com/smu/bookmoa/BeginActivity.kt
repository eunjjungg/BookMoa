package com.smu.bookmoa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.smu.bookmoa.databinding.ActivityBeginBinding
import java.util.*

class BeginActivity : AppCompatActivity(), View.OnClickListener {

    val binding by lazy { ActivityBeginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }



    override fun onClick(p0: View?) {


        when(p0?.id){
            binding.btnFindpw.id -> {
                val intentFindpw = Intent(this, Begin_Findpw::class.java)
                startActivity(intentFindpw)
            }
            binding.btnRegister.id -> {
                val intentRegister = Intent(this, Begin_Register::class.java)
                startActivity(intentRegister)
            }
            binding.btnLogin.id -> {
                val intentLogin = Intent(this, Begin_Login::class.java)
                startActivity(intentLogin)
            }
        }
    }
}