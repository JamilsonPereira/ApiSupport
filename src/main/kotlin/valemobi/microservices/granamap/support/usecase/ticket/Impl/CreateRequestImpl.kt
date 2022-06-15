package valemobi.microservices.granamap.support.usecase.ticket.Impl


import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.config.SupportProperties
import valemobi.microservices.granamap.support.adapter.controller.dto.response.UserResponse
import valemobi.microservices.granamap.support.adapter.gateway.web.WebGateway
import valemobi.microservices.granamap.support.domain.Attachment
import valemobi.microservices.granamap.support.domain.Request
import valemobi.microservices.granamap.support.domain.Ticket
import valemobi.microservices.granamap.support.usecase.ticket.CreateRequest
import valemobi.microservices.granamap.support.usecase.ticket.CreateTicket
import java.util.*

@Service
class CreateRequestImpl(
    private val webGateway: WebGateway,
    private val supportProperties: SupportProperties

) : CreateRequest {
    override fun execute(domain: Request, token: String): Mono<Void> {

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
                                webGateway.createRequest(supportProperties.username, supportProperties.token, domain)
                            }

                    }
            }
    }

    private fun createMessege(
        domain: Request,
        user: UserResponse
    ) = "${domain.body}\n \n Nome: ${user.name}\n Email: ${user.email}"
}