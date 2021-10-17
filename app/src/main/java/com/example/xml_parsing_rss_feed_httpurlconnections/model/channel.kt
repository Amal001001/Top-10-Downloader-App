package com.example.xml_parsing_rss_feed_httpurlconnections.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable


    @Root(name = "channel", strict = false)
    class channel @JvmOverloads constructor(

    @field:ElementList(inline = true, name = "item")
    var item: List<item>? = null

    ) : Serializable {
    }