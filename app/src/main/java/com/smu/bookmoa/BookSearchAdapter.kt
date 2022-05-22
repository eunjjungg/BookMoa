package com.smu.bookmoa

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
            BookWirteDataStore().storeBookData(bookResult.items.get(position), date)
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


//            itemView.setOnClickListener({
//
//                // 여기에 firebase store하는 작업이 필요
//                Toast.makeText(view.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
//            })
        }
    }

}