package valemobi.microservices.granamap.support.adapter.gateway.web.response

import com.fasterxml.jackson.annotation.JsonAlias

class TicketResponseDto {
    @JsonAlias("ticket")
    var resultTicket: ResultTicket? = null
}

class ResultTicket {
    @JsonAlias("id")
    var id: Long? = null
}