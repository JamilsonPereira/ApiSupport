package valemobi.microservices.granamap.support.adapter.controller.mapper

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.request.TicketRequest
import valemobi.microservices.granamap.support.domain.Request
import valemobi.microservices.granamap.support.domain.Ticket

@Component
class RequestMapperDomainAndDto {
    fun convertDomainToRequest(domain: Mono<Request>): Mono<TicketRequest> {
        return domain.map {
            TicketRequest(
                it.subject,
                it.body,
                it.contactReason,
                it.attachment!!
            )
        }
    }
    fun convertRequestToDomain(request: TicketRequest): Request{
        return Request(request.subject, request.body, request.contactReason, request.attachment)
    }
}