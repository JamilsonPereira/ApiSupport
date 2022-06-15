package valemobi.microservices.granamap.support.adapter.gateway.mapper

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.request.TicketRequest
import valemobi.microservices.granamap.support.adapter.gateway.web.response.*
import valemobi.microservices.granamap.support.domain.Request
import valemobi.microservices.granamap.support.domain.Ticket
import valemobi.microservices.granamap.support.domain.enums.ContactReason

@Component
class RequestMapperDtoDomain() {
    fun convertDomainToDto(domain: Request): RequestDto {
        var ticketDto = RequestDto()
        var bodyTicket = BodyRequest()
        var comment = CommentRequest()
        comment.body = domain.body
        bodyTicket.subject = subjectGranaMap(domain)
        bodyTicket.description = domain.contactReason
        bodyTicket.tags = domain.tags
        comment.uploads = mutableListOf(domain.attachment!!)
        bodyTicket.comment = comment
        ticketDto.request = bodyTicket

        return ticketDto
    }

    private fun subjectGranaMap(domain: Request) =
        "GranaMap - " + domain.contactReason.name.lowercase().replaceFirstChar { first -> first.uppercase() }
}