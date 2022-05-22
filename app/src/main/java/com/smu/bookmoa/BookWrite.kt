package com.smu.bookmoa

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.smu.bookmoa.databinding.ActivityBookWriteBinding
import kotlinx.android.synthetic.main.activity_book_write.*
import java.lang.Exception
import java.util.*

class BookWrite : AppCompatActivity() {

    //갤러리 용 상수
    private val OPEN_GALLERY = 1
    val binding by lazy { ActivityBookWriteBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dateText = intent.getStringExtra("date")

        // spinner 관련 설정
        val platformArr:Array<String> = resources.getStringArray(R.array.arrPlatforms)
        val spnAdapter = ArrayAdapter<String>(this@BookWrite, android.R.layout.simple_spinner_item, platformArr)
        val spinnerPlatform = findViewById<Spinner>(R.id.spinnerPlatform)

        spinnerPlatform.adapter = spnAdapter
        spinnerPlatform.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                if(position != 0){
                    Toast.makeText(this@BookWrite, platformArr[position],
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        // imageView 갤러리에서 불러오는 것 관련 설정
        val imgCover = findViewById<ImageView>(R.id.imgCover)
        imgCover.setOnClickListener {
            openGallery()
        }

        // title date로 설정하는 부분
        val tvDate = findViewById<TextView>(R.id.tvDate)
        tvDate.setText(dateText)

        // back button
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        // Search book button
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this, BookWriteSearch::class.java)
            intent.putExtra("date", dateText)
            startActivity(intent)
        }
    }

    // Gallery 열도록 하는 함수
    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == OPEN_GALLERY) {
                var currentImageURl : Uri? = data?.data

                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageURl)
                    imgCover.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Log.d("so", "mething wrong")
            }
        }
    }
}