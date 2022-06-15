package valemobi.microservices.granamap.support.usecase.ticket

import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.domain.Attachment
import valemobi.microservices.granamap.support.domain.Ticket

interface CreateTicket {
    fun execute (domain: Ticket, token : String): Mono<Void>
}