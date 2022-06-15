package valemobi.microservices.granamap.support.adapter.gateway.web.response

import com.fasterxml.jackson.annotation.JsonAlias
import valemobi.microservices.granamap.support.domain.enums.ContactReason


class TicketDto {
    @JsonAlias("ticket")
    var ticket: BodyTicket? = null
}

class BodyTicket {
    @JsonAlias("subject")
    var subject: String? = ""

    @JsonAlias("comment")
    var comment: Comment? = null

    @JsonAlias("tags")
    var tags: MutableList<String>? = mutableListOf()

    @JsonAlias("description")
    var description: ContactReason? = null


}

class Comment {
    @JsonAlias("body")
    var body: String? = ""

    @JsonAlias("uploads")
    var uploads: MutableList<String>? = mutableListOf()
}

