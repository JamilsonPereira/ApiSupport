package valemobi.microservices.granamap.support.adapter.gateway.web.response

import com.fasterxml.jackson.annotation.JsonAlias
import valemobi.microservices.granamap.support.domain.enums.ContactReason


class RequestDto {
    @JsonAlias("request")
    var request: BodyRequest? = null
}

class BodyRequest {
    @JsonAlias("subject")
    var subject: String? = ""

    @JsonAlias("comment")
    var comment: CommentRequest? = null

    @JsonAlias("tags")
    var tags: MutableList<String>? = mutableListOf()

    @JsonAlias("description")
    var description: ContactReason? = null


}

class CommentRequest {
    @JsonAlias("body")
    var body: String? = ""

    @JsonAlias("uploads")
    var uploads: MutableList<String>? = mutableListOf()
}

