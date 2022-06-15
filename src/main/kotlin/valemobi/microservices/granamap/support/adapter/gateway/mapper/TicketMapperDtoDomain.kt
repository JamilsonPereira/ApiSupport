package valemobi.microservices.granamap.support.adapter.gateway.mapper

import org.springframework.stereotype.Component
import valemobi.microservices.granamap.support.adapter.gateway.web.response.Comment
import valemobi.microservices.granamap.support.adapter.gateway.web.response.BodyTicket
import valemobi.microservices.granamap.support.adapter.gateway.web.response.TicketDto
import valemobi.microservices.granamap.support.domain.Ticket
import valemobi.microservices.granamap.support.domain.enums.ContactReason

@Component
class TicketMapperDtoDomain() {
    fun convertDomainToDto(domain: Ticket): TicketDto {
        var ticketDto = TicketDto()
        var bodyTicket = BodyTicket()
        var comment = Comment()
        comment.body = domain.body
        bodyTicket.subject = subjectGranaMap(domain)
        bodyTicket.description = domain.contactReason
        bodyTicket.tags = domain.tags
        comment.uploads = mutableListOf(domain.attachment!!)
        bodyTicket.comment = comment
        ticketDto.ticket = bodyTicket

        return ticketDto
    }

    private fun subjectGranaMap(domain: Ticket) =
        "GranaMap - " + domain.contactReason.name.lowercase().replaceFirstChar { first -> first.uppercase() }
}