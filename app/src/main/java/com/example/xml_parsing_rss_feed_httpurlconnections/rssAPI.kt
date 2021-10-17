package com.example.xml_parsing_rss_feed_httpurlconnections

import retrofit2.Call
import retrofit2.http.GET

interface rssAPI {
    @get:GET("apps.rss")
    val rss: Call<rss?>?

    companion object {
        const val BASE_URL = "https://rss.applemarketingtools.com/api/v2/us/apps/top-free/10/"
    }
}