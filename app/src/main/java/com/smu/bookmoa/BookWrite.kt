package com.smu.bookmoa

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.smu.bookmoa.databinding.ActivityBookWriteBinding
import com.smu.bookmoa.room.AppDatabase
import com.smu.bookmoa.room.UsingDateDataFun
import com.smu.bookmoa.room.WriteDateData

import kotlinx.android.synthetic.main.activity_book_write.*
import java.lang.Exception
import java.util.*

class BookWrite : AppCompatActivity() {

    //startActivtyForResult용 상수
    private val OPEN_GALLERY = 1
    private val OPEN_WRITE = 2

    //이미지뷰용 변수
    //갤러리에서 이미지 업로드 시 uri 값이 들어가고
    //search로 책 가져올 시 null이 할당 됨
    private var updateImageUriForGallery: Uri? = null
    //api 사용한 이미지가 있을 경우
    private var updateImageUriForApi: String? = null

    //binding
    val binding by lazy { ActivityBookWriteBinding.inflate(layoutInflater)}

    //cloud firestore 용 변수
    var fbAuth : FirebaseAuth? = null
    var fbFirestore : FirebaseFirestore? = null
    var fbStorage : FirebaseStorage?= null

    lateinit var dateText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        accessDBForGetData()

        dateText = intent.getStringExtra("date")!!

        // spinner 관련 설정
        val platformArr:Array<String> = resources.getStringArray(R.array.arrPlatforms)
        val spnAdapter = ArrayAdapter<String>(this@BookWrite, android.R.layout.simple_spinner_item, platformArr)
        val spinnerPlatform = findViewById<Spinner>(R.id.spinnerPlatform)
        spinnerPlatform.adapter = spnAdapter

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
            startActivityForResult(intent, OPEN_WRITE)
        }

        binding.btnRegister.setOnClickListener {
            if(binding.etTitle.text == null || binding.etTitle.text.toString() == ""){
                Toast.makeText(this@BookWrite, "제목은 필수로 입력해주십시오", Toast.LENGTH_SHORT).show()
            }
            else {
                val updateBookStoreData = BookStoreData()
                updateBookStoreData.author = binding.etAuthor.text.toString()
                updateBookStoreData.publisher = binding.etPublisher.text.toString()
                updateBookStoreData.title = binding.etTitle.text.toString()
                updateBookStoreData.platform = binding.spinnerPlatform.selectedItemPosition
                updateBookStoreData.review = binding.etReview.text.toString()

                //gallery로 업로드한걸로 register하고 싶을 시 아래 작업 수행
                if (updateImageUriForGallery != null) {
                    //갤러리에서 업로드 시 생성되는 파일명 규칙
                    var dateForCloud = intent
                        .getStringExtra("date")
                        .toString()
                        .replace(" / ", "_")
                    updateBookStoreData.coverImg = "gallery" + dateForCloud + ".jpg"
                } else {
                    updateBookStoreData.coverImg = null
                }

                accessDBForUpdateData(updateBookStoreData)
            }
        }

    }


    // Gallery 열도록 하는 함수
    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    // Gallery 상세 처리 & write search api에서 돌아올 때 자동 저장
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == OPEN_GALLERY) {
                var currentImageURl : Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media
                        .getBitmap(contentResolver, currentImageURl)
                    imgCover.setImageBitmap(bitmap)
                    updateImageUriForGallery = currentImageURl
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else if(requestCode == OPEN_WRITE) {
                //내부 저장소로 바꿀 필요 있음!!!!!!!! <caution>
                //search한 다음에 뒤로가기 눌렀을 때
                //sql 써서 텍스트랑 cover image까지 다 설정
                accessDBForGetData()
                binding.spinnerPlatform.setSelection(0)
            }
            else {
                Log.d("so", "mething wrong")
            }
        }
    }

    private fun accessDBForGetData() {
        this.fbAuth = FirebaseAuth.getInstance()
        this.fbFirestore = FirebaseFirestore.getInstance()

        var dateForCloud = intent
            .getStringExtra("date")
            .toString()
            .replace(" / ","_")

        fbFirestore
            ?.collection("book/" + fbAuth?.uid.toString() + "/" + dateForCloud)
            ?.document("data")
            ?.get()
            ?.addOnCompleteListener {
                if(it.isSuccessful) {
                    var bookDTO = it.result?.toObject(BookStoreData::class.java)
                    //read한 위치에 data가 있을 경우 나머지 업데이트
                    if(bookDTO != null) {
                        //전체 데이터 초기화하고 구매처 또한 기본으로 돌아가도록 설정
                        updateWriteAll(bookDTO)
                    }
                }
                else {
                    Log.d("bookDTO", "nothing")
                }
            }



    }

    private fun accessDBForUpdateData(updateBookStoreData: BookStoreData) {
        this.fbAuth = FirebaseAuth.getInstance()
        this.fbFirestore = FirebaseFirestore.getInstance()
        this.fbStorage = FirebaseStorage.getInstance()
        var dateForCloud = intent
            .getStringExtra("date")
            .toString()
            .replace(" / ","_")

        //커버 이미지가 없으면 null 할당
        //커버 이미지가 갤러리에서 꺼낸 이미지가 아니면 url 그대로 할당
        if(updateBookStoreData.coverImg == null)
            updateBookStoreData.coverImg = updateImageUriForApi
        //커버 이미지가 갤러리에서 꺼낸 이미지일 경우 처리
        else {
            // <caution> 모르겠음
            // 1) cloud storage에 올리고
            var imgFileName = "IMAGE_" + dateForCloud + ".png"
            var fbStorageRef = fbStorage?.reference?.child("images")?.child(imgFileName)
            fbStorageRef?.putFile(updateImageUriForGallery!!)?.addOnSuccessListener {

            }
            // 2) 올린 사진의 uri를 가져와서 그걸
            // updateBookStoreData.coverImg에 저장
            // 그리고 하단 처리 동일

        }

        fbFirestore
            ?.collection("book/" + fbAuth?.uid.toString() + "/" + dateForCloud)
            ?.document("data")
            ?.set(updateBookStoreData)
            ?.addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(this@BookWrite, "저장 완료",
                        Toast.LENGTH_SHORT).show()
                    UsingDateDataFun().insertDate(applicationContext, dateText)
                }
                else {
                    Log.d("bookDTO", "nothing")
                }
            }

        finish()



    }

    //db대로 그냥 보이게 하는 함수
    private fun updateWriteAll(bookData: BookStoreData) {
        if(bookData.author != null) {
            binding.etAuthor.setText(bookData.author)
        }
        if(bookData.coverImg != null) {
            updateImageUriForGallery = null
            updateImageUriForApi = bookData.coverImg

            Glide.with(binding.root).load(bookData.coverImg)
                .apply(RequestOptions().override(100, 150))
                .apply(RequestOptions().centerCrop())
                .into(binding.imgCover)
        }
        if(bookData.platform != null) {
            binding.spinnerPlatform.setSelection(bookData.platform!!)
        }
        if(bookData.publisher != null) {
            binding.etPublisher.setText(bookData.publisher)
        }
        if(bookData.title != null) {
            binding.etTitle.setText(bookData.title)
        }
        if(bookData.review != null){
            binding.etReview.setText(bookData.review)
        }

    }



}