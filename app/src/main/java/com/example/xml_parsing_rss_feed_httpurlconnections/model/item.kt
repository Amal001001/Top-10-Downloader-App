package com.example.xml_parsing_rss_feed_httpurlconnections.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "item", strict = false)
class item constructor() : Serializable {

    @field:Element(name = "description")
    var description: String? = null

}