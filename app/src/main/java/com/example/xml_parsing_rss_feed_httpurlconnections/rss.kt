package com.example.xml_parsing_rss_feed_httpurlconnections


import com.example.xml_parsing_rss_feed_httpurlconnections.model.channel
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "rss", strict = false)
class rss constructor() : Serializable   {

    @field:Element(name = "channel")
    var channel : channel? = null
}