package com.smu.bookmoa

import androidx.recyclerview.widget.RecyclerView
import com.smu.bookmoa.databinding.EventItemBinding

class EventViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: EventInfo) {
        binding.tvEvent.text = item.name
    }
    fun setImg(item: EventInfo) {
        when(item.platform) {
            "리디북스" -> binding.imgPlatform.setImageResource(R.drawable.ic_ridi)
            "예스24" -> binding.imgPlatform.setImageResource(R.drawable.ic_yes24)
            "알라딘" -> binding.imgPlatform.setImageResource(R.drawable.ic_aladin)
        }
    }
}

