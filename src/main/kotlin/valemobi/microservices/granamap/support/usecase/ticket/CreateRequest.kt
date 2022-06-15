package valemobi.microservices.granamap.support.usecase.ticket

import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.domain.Attachment
import valemobi.microservices.granamap.support.domain.Request
import valemobi.microservices.granamap.support.domain.Ticket

interface CreateRequest {
    fun execute (domain: Request, token : String): Mono<Void>
}