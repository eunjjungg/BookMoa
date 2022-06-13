package com.smu.bookmoa

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smu.bookmoa.databinding.EventItemBinding

class EventListAdapter(val itemList : List<EventInfo>) : RecyclerView.Adapter<EventViewHolder>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.setImg(itemList[position])
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, Main_event_det::class.java)
            intent.putExtra("name", itemList[position].name)
            intent.putExtra("period", itemList[position].period)
            intent.putExtra("platform", itemList[position].platform)
            intent.putExtra("eventUrl", itemList[position].eventUrl)
            intent.putExtra("img", itemList[position].image)

            it.context.startActivity(intent)
        })
    }
}

data class EventInfo (
    val name : String,
    val image : String,
    val period : String,
    val eventUrl : String,
    val platform : String
)