package com.smu.bookmoa

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smu.bookmoa.room.AppDatabase
import java.util.*

class BookWirteDataStore {

    var fbAuth : FirebaseAuth? = null
    var fbFirestore : FirebaseFirestore? = null

    fun storeBookData(data: Item, date: String) : Boolean {
        var isSuccess: Boolean = false
        this.fbAuth = FirebaseAuth.getInstance()
        this.fbFirestore = FirebaseFirestore.getInstance()

        if(true){
            var bookStoreData = BookStoreData()
            bookStoreData.uid = fbAuth?.uid
            bookStoreData.userId = fbAuth?.currentUser?.email
            bookStoreData.title = data.title.replace("</b>", "").replace("<b>", "")
            bookStoreData.author = data.author.replace("</b>", "").replace("<b>", "")
            bookStoreData.publisher = data.publisher.replace("</b>", "").replace("<b>", "")
            bookStoreData.coverImg = data.image
            bookStoreData.timeStamp = date

            //book/uid/date/data
            //ex: /book/d3CjSN8eHiMl7gWhnBQBlCnYtHR2/5_11/data
            fbFirestore?.collection("book/" + bookStoreData.uid.toString() + "/" + date)
                ?.document("data")
                ?.set(bookStoreData)

            isSuccess = true
        }



        return isSuccess
    }



}