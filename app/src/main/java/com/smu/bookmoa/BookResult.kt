package com.smu.bookmoa

import android.media.Image
import com.google.gson.annotations.SerializedName

data class BookResult (val items: List<Item>)

data class Item(
    val title: String,

    val author: String,

    val publisher: String,

    val image: String
)
