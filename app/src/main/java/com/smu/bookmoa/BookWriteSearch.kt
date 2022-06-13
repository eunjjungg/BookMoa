package com.smu.bookmoa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smu.bookmoa.databinding.ActivityBookWriteSearchBinding
import org.json.JSONException
import org.json.JSONObject
import retrofit2.*
import com.google.gson.GsonBuilder

class BookWriteSearch : AppCompatActivity() {

    val binding by lazy { ActivityBookWriteSearchBinding.inflate(layoutInflater)}

    // naver api client
    private val client_id: String = "HmVYbLp0Rqf5Y39lheku"
    private val client_pw: String = "En3f0GhedD"
    lateinit var apiInterface: ApiInterface


    // 검색 결과 개수
    val displayAmount: Int = 10

    // BookSearchAdapter로 클릭한 날짜 전달 위한 변수
    lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        date = intent.getStringExtra("date").toString()

        // recycler view adapter 설정
        apiInterface =
            ApiClient().getInstance().create(ApiInterface::class.java)


        // back button
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        // search edittext 키보드 완료 누르면 내려가도록 설정
        binding.etSearch.setOnEditorActionListener { textView, action, keyEvent ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                // 키보드 내리기
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
                handled = true
            }
            handled
        }

        // search button
        binding.btnSearch.setOnClickListener {
            binding.recyclerBookResult.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            binding.recyclerBookResult.setHasFixedSize(true)
            var searchText = binding.etSearch.text.toString()
            getResultSearch(searchText)
        }



    }

    // naver api로 검색
    fun getResultSearch(searchText: String) {
        val call =
            apiInterface.getSearchResult(client_id, client_pw, "$searchText", displayAmount);

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful() && response.body() != null) {
                    val result = response.body().toString()

                    //json을 kotlin에서 사용 가능한 obj로 만들기 위한 변환
                    val gson = GsonBuilder().create()
                    val bookResult = gson.fromJson(result, BookResult::class.java)

                    runOnUiThread {
                        binding.recyclerBookResult.adapter = BookSearchAdapter(bookResult, this@BookWriteSearch, date)
                    }

                } else {
                    Log.e("bookR", "fail")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("error", "err : " + t.message)
            }

        })
    }


}