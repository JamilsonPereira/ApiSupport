package valemobi.microservices.granamap.support.adapter.controller.mapper

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.request.TicketRequest
import valemobi.microservices.granamap.support.domain.Ticket

@Component
class TicketMapperDomainAndDto {
    fun convertDomainToRequest(domain: Mono<Ticket>): Mono<TicketRequest> {
        return domain.map {
            TicketRequest(
                it.subject,
                it.body,
                it.contactReason,
                it.attachment!!
            )
        }
    }
    fun convertRequestToDomain(request: TicketRequest): Ticket{
        return Ticket(request.subject, request.body, request.contactReason, request.attachment)
    }
}