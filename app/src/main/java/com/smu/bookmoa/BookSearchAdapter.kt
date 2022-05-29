package com.smu.bookmoa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.smu.bookmoa.room.AppDatabase
import com.smu.bookmoa.room.UsingDateDataFun
import com.smu.bookmoa.room.WriteDateData
import kotlinx.android.synthetic.main.book_item.view.*
import java.util.*

class BookSearchAdapter
    (val bookResult: BookResult, val mContext: Context, val date: String)
    :RecyclerView.Adapter<BookSearchAdapter.ViewHolder>(){

    private val activity : Activity = mContext as Activity

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookSearchAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookSearchAdapter.ViewHolder, position: Int) {
        holder.bindItems(bookResult.items.get(position))
        holder.itemView.setOnClickListener {
            val isSuccess = BookWirteDataStore()
                .storeBookData(bookResult.items.get(position), date.replace(" / ","_"))
            if(isSuccess) {
                Toast.makeText(holder.view.context, "저장 완료", Toast.LENGTH_SHORT).show()
                UsingDateDataFun().insertDate(holder.view.context, date)
            }
            else {
                Toast.makeText(holder.view.context, "저장 실패, 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
            val intentFinish: Intent = Intent()
            activity.setResult(Activity.RESULT_OK, intentFinish)
            activity.finish()
        }
    }

    override fun getItemCount(): Int {
        return bookResult.items.count()
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data: Item) {
            //검색 결과의 이미지를 view로 넣기 위한 부분
            Glide.with(view.context).load(data.image)
                .apply(RequestOptions().override(100, 150))
                .apply(RequestOptions().centerCrop())
                .into(view.imgCover)
            //검색 결과에 <b> </b> 가 붙어서 나오기 때문에 제거하기 위해 replace 사용
            itemView.tvAuthor.text =
                "저자 : ${data.author.replace("</b>", "").replace("<b>", "")}"
            itemView.tvPublisher.text =
                "출판사 : ${data.publisher.replace("</b>", "").replace("<b>", "")}"
            itemView.tvTitle.text =
                "제목 : ${data.title.replace("</b>", "").replace("<b>", "")}"
        }

    }



}