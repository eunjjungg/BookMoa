package com.smu.bookmoa

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class BookWirteDataStore {

    var fbAuth : FirebaseAuth? = null
    var fbFirestore : FirebaseFirestore? = null

    fun storeBookData(data: Item, date: String) {
        this.fbAuth = FirebaseAuth.getInstance()
        this.fbFirestore = FirebaseFirestore.getInstance()

        if(true){
            var bookStoreDate = BookStoreDate()

            bookStoreDate.uid = fbAuth?.uid
            bookStoreDate.userId = fbAuth?.currentUser?.email
            bookStoreDate.date = date
            fbFirestore?.collection("book")
                ?.document(fbAuth?.uid.toString())?.set(bookStoreDate)
        }
    }
}