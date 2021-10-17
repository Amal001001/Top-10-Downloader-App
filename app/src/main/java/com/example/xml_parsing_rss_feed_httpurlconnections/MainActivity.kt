package com.example.xml_parsing_rss_feed_httpurlconnections

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xml_parsing_rss_feed_httpurlconnections.model.item
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {

    private  val BASE_URL = "https://rss.applemarketingtools.com/api/v2/us/apps/top-free/10/"

    lateinit var rv: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private var itemArray = mutableListOf<item>()

    lateinit var bFetch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        rvAdapter = RVAdapter(itemArray)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        bFetch = findViewById(R.id.bFetch)

        CoroutineScope(Dispatchers.IO).launch {

            val results = coroutinFun()
            Log.d("main", "CoroutineScope!")

        }
    }
        suspend fun coroutinFun(){

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
            val rssAPI = retrofit.create(rssAPI::class.java)
            val call = rssAPI.rss

            bFetch.setOnClickListener(object : View.OnClickListener {
              override fun onClick(v: View?) {

                call!!.clone().enqueue(object : Callback<rss?> {
                    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
                    override fun onResponse(call: Call<rss?>, response: Response<rss?>) {

                        val entries = response.body()!!.channel?.item
                        for (one in entries!!) {
                            Log.d("main", "onResponse: " + one.description)

                            itemArray.add(one)
                            rvAdapter.notifyDataSetChanged()
                        }
                        bFetch.isClickable = false
                    }

                    override fun onFailure(call: Call<rss?>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT)
                            .show()
                    }
                })

              }

            })
        }
}

//Parse XML Using Retrofit
//private  val BASE_URL = "https://rss.applemarketingtools.com/api/v2/us/apps/top-free/10/"
//
//lateinit var rv: RecyclerView
//private lateinit var rvAdapter: RVAdapter
//private var itemArray = mutableListOf<item>()
//
////    lateinit var tv: TextView
//lateinit var bFetch: Button
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    rv = findViewById(R.id.rv)
//    rvAdapter = RVAdapter(itemArray)
//    rv.adapter = rvAdapter
//    rv.layoutManager = LinearLayoutManager(this)
//
////        tv = findViewById(R.id.tv)
//    bFetch = findViewById(R.id.bFetch)
//
//    val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(SimpleXmlConverterFactory.create())
//        .build()
//    val rssAPI = retrofit.create(rssAPI::class.java)
//    val call = rssAPI.rss
//
//    bFetch.setOnClickListener(object : View.OnClickListener {
//        override fun onClick(v: View?) {
//
//            call!!.clone().enqueue(object : Callback<rss?> {
//                @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
//                override fun onResponse(call: Call<rss?>, response: Response<rss?>) {
//
//                    val entries = response.body()!!.channel?.item
//                    for (one in entries!!) {
//                        Log.d("main", "onResponse: " +one.description )
////                            var text = tv.text.toString()
////                            tv.text = "$text + \n + ${item.title}"
//                        itemArray.add(one)
//                        rvAdapter.notifyDataSetChanged()
//                    }
//                    bFetch.isClickable = false
//                }
//
//                override fun onFailure(call: Call<rss?>, t: Throwable) {
//                    Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
//                }
//            })
//
//        }
//
//    })
