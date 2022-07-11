package com.smu.bookmoa

import java.util.*



data class BookStoreData(
    var timeStamp: String? = null,
    var uid: String? = null,
    var userId: String? = null,
    var title: String? = null,
    var author: String? = null,
    var coverImg: String? = null,
    var publisher: String? = null,
    var platform: Int? = null,
    var review: String? = null
)
