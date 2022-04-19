package com.smu.bookmoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.smu.bookmoa.databinding.ActivityBeginLoginBinding

class Begin_Login : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityBeginLoginBinding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            binding.btnLogIn.id -> {
                val email = binding.etId.text.toString()
                val password = binding.etPw.text.toString()
                logInProcess(email, password)
            }
        }
    }

    fun logInProcess(email:String, password:String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    Toast.makeText(baseContext, "Sign in Success.", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}