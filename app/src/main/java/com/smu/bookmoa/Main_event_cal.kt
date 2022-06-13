package com.smu.bookmoa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.smu.bookmoa.databinding.FragmentMainEventCalBinding
import org.jsoup.Jsoup
import java.text.SimpleDateFormat

class Main_event_cal : Fragment() {

    val binding by lazy { FragmentMainEventCalBinding.inflate(layoutInflater)}
    var eventList : ArrayList<EventInfo> = arrayListOf()
    val result = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnFilter.setOnClickListener {
            startActivityForResult(Intent(activity, Main_event_set::class.java), result)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentTime : Long = System.currentTimeMillis()
        val dataFormat = SimpleDateFormat("yy MM dd E")
        val currentDate = dataFormat.format(currentTime)
        binding.tvDate.text = currentDate

        Thread(kotlinx.coroutines.Runnable {
            crawl("https://ridibooks.com/event/fantasy", "ul.event_list_wrapper li.event_list", "h3.event_title a", "img", "li.contents_descript span.descript_body")
            crawl("http://www.yes24.com/EventWorld/ebook.aspx?CategoryNumber=017&SC=000", "table tr td", "a b", "a img", "span.infoGrays")
            crawl("https://www.aladin.co.kr/events/wevent_main_ebook.aspx", "tr td", "a h3", "div.bn IMG", "span.date")
            activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                binding.eventView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.eventView.adapter = EventListAdapter(eventList)
            })
        }).start()
        
        return binding.root
    }
//
//    override fun onResume() {
//        super.onResume()
//
//        filter()
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == result)
            filter(data)
    }

    fun filter(data: Intent?) {
        var selectedEvents: ArrayList<EventInfo> = arrayListOf()

        if (data != null) {
            if(data.extras?.getBooleanArray("check")?.get(0) == true) {
                for(item in eventList) {
                    if(item.platform == "리디북스")
                        selectedEvents.add(item)
                }
            }
            if(data.extras?.getBooleanArray("check")?.get(1) == true) {
                for(item in eventList) {
                    if(item.platform == "예스24")
                        selectedEvents.add(item)
                }
            }
            if(data.extras?.getBooleanArray("check")?.get(2) == true) {
                for(item in eventList) {
                    if(item.platform == "알라딘")
                        selectedEvents.add(item)
                }
            }
        }
        else
            selectedEvents = eventList
        binding.eventView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.eventView.adapter = EventListAdapter(selectedEvents)
    }
//        for(item in eventList) {
//            if(item.platform == "예스24" || item.platform == "알라딘")
//                selectedEvents.add(item)
//        }


    fun crawl(url : String, tag_doc : String, tag_name : String, tag_img : String, tag_period : String) {
        val doc = Jsoup.connect(url).get()
        val today = doc.select(tag_doc)
        today.forEach { item ->
            val event_name = item.select(tag_name).text()
            val event_image = item.select(tag_img).attr("src")
            var event_period = item.select(tag_period).text()
            val event_url = item.select("a").attr("href")
            var event_platform = ""
            if(url.contains("ridibooks")) {
                event_period = item.select(tag_period)[0].text()
                event_platform = "리디북스"
            }
            else if(url.contains("yes24"))
                event_platform = "예스24"
            else if(url.contains("aladin"))
                event_platform = "알라딘"
            if(event_name.isNotEmpty() && event_image.isNotEmpty() && event_url.isNotEmpty() && event_period.isNotEmpty() && event_period.length < 50)
                eventList.add(EventInfo(event_name, event_image, event_period, event_url, event_platform))
        }
    }

}

