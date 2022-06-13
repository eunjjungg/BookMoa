package com.smu.bookmoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.smu.bookmoa.databinding.ActivitySetLockPwBinding

class Main_set_lock_pw : AppCompatActivity() {

    val binding by lazy {ActivitySetLockPwBinding.inflate(layoutInflater)}
    private var oldPw = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.pw1.requestFocus()
        val btnArray = arrayListOf<Button>(binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4, binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9, binding.btnDel)
        for(btn in btnArray)
            btn.setOnClickListener(btnListener)
    }

    private val btnListener = View.OnClickListener {
        var currentValue = -1
        when(it.id) {
            binding.btn0.id -> currentValue = 0
            binding.btn1.id -> currentValue = 1
            binding.btn2.id -> currentValue = 2
            binding.btn3.id -> currentValue = 3
            binding.btn4.id -> currentValue = 4
            binding.btn5.id -> currentValue = 5
            binding.btn6.id -> currentValue = 6
            binding.btn7.id -> currentValue = 7
            binding.btn8.id -> currentValue = 8
            binding.btn9.id -> currentValue = 9
            binding.btnDel.id -> onDeleteKey()
        }
        val strCurrentValue = currentValue.toString()
        if (currentValue != -1){
            when {
                binding.pw1.isFocused -> {
                    setEditText(binding.pw1, binding.pw2, strCurrentValue)
                }
                binding.pw2.isFocused -> {
                    setEditText(binding.pw2, binding.pw3, strCurrentValue)
                }
                binding.pw3.isFocused -> {
                    setEditText(binding.pw3, binding.pw4, strCurrentValue)
                }
                binding.pw4.isFocused -> {
                    binding.pw4.setText(strCurrentValue)
                }
            }
        }

        if (binding.pw1.text.isNotEmpty() && binding.pw2.text.isNotEmpty() && binding.pw3.text.isNotEmpty() && binding.pw4.text.isNotEmpty()) {
            inputType(intent.getIntExtra("type", 1))
        }
    }

    private fun onDeleteKey() {
        when {
            binding.pw1.isFocused ->
                binding.pw1.setText("")
            binding.pw2.isFocused -> {
                binding.pw1.setText("")
                binding.pw1.requestFocus()
            }
            binding.pw3.isFocused -> {
                binding.pw2.setText("")
                binding.pw2.requestFocus()
            }
            binding.pw4.isFocused -> {
                binding.pw4.setText("")
                binding.pw3.setText("")
                binding.pw3.requestFocus()
            }
        }
    }

    private fun setEditText(current: EditText, next: EditText, strCurrentValue: String) {
        current.setText(strCurrentValue)
        next.requestFocus()
        next.setText("")
    }

    private fun inputType(type: Int) {
        when(type) {
            AppLockConst.ENABLE_PASSLOCK -> {
                if(oldPw.isEmpty()) {
                    oldPw = inputedPassword()
                    onClear()
                    binding.pageName.text = "비밀번호 확인"
                }
                else {
                    if(oldPw == inputedPassword()) {
                        AppLock(this).setLock(inputedPassword())
                        finish()
                    }
                    else {
                        binding.pageName.text = "비밀번호가 틀립니다."
                        onClear()
                    }
                }
            }
            AppLockConst.DISABLE_PASSLOCK -> {
                if(AppLock(this).isLockSet()) {
                    if(AppLock(this).checkPw(inputedPassword())) {
                        AppLock(this).removeLock()
                        finish()
                    }
                }
                else
                    finish()
            }
            AppLockConst.UNLOCK_PASSWORD -> {
                if (AppLock(this).checkPw(inputedPassword())) {
                    finish()
                } else {
                    binding.pageName.text = "비밀번호가 틀립니다."
                    onClear()
                }
            }
        }
    }

    private fun onClear() {
        binding.pw1.setText("")
        binding.pw2.setText("")
        binding.pw3.setText("")
        binding.pw4.setText("")
        binding.pw1.requestFocus()
    }

    private fun inputedPassword(): String {
        return "${binding.pw1.text}${binding.pw2.text}${binding.pw3.text}${binding.pw4.text}"
    }
}