package valemobi.microservices.granamap.support.usecase.ticket.Impl


import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.config.SupportProperties
import valemobi.microservices.granamap.support.adapter.controller.dto.response.UserResponse
import valemobi.microservices.granamap.support.adapter.gateway.web.WebGateway
import valemobi.microservices.granamap.support.domain.Attachment
import valemobi.microservices.granamap.support.domain.Ticket
import valemobi.microservices.granamap.support.usecase.ticket.CreateTicket
import java.util.*

@Service
class CreateTicketImpl(
    private val webGateway: WebGateway,
    private val supportProperties: SupportProperties

) : CreateTicket {
    override fun execute(domain: Ticket, token: String): Mono<Void> {

        return Mono.just(domain)
            .flatMap { ticket ->

                var uploadAttachment: Mono<Attachment> = Mono.just(Attachment())
                domain.tags = supportProperties.tags

                if (ticket.attachment!=null && ticket.attachment!!.isNotEmpty() ) {
                    val imgBytes: ByteArray = Base64.getDecoder().decode(ticket.attachment)

                    uploadAttachment =
                        webGateway.uploadAttachment(supportProperties.username, supportProperties.token, imgBytes)
                }
                uploadAttachment
                    .flatMap { attachment ->
                        domain.attachment =  attachment.token!!

                        webGateway.findUser(token)
                            .flatMap { user ->
                                domain.body = createMessege(domain, user)
                                webGateway.createTicket(supportProperties.username, supportProperties.token, domain)
                            }

                    }
            }
    }

    private fun createMessege(
        domain: Ticket,
        user: UserResponse
    ) = "${domain.body}\n \n Nome: ${user.name}\n Email: ${user.email}"
}