package valemobi.microservices.granamap.support.adapter.gateway.web.response

import com.fasterxml.jackson.annotation.JsonAlias

class TagDto{
    @JsonAlias("tags")
    var tags: MutableList<String>? = mutableListOf()
}