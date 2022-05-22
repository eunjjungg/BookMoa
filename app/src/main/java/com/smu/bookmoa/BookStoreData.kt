package com.smu.bookmoa

import java.util.*

data class BookStoreDate(
    var uid: String? = null,
    var userId: String? = null,
    var date: String? = null,
)

data class BookStoreData(
    var title: String? = null,
    var author: String? = null,
    var coverImg: String? = null,
    var publisher: String? = null
)
