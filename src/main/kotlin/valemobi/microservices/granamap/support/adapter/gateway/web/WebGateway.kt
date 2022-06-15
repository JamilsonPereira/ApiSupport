package valemobi.microservices.granamap.support.adapter.gateway.web

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.response.UserResponse
import valemobi.microservices.granamap.support.domain.Attachment
import valemobi.microservices.granamap.support.domain.Faq
import valemobi.microservices.granamap.support.domain.Request
import valemobi.microservices.granamap.support.domain.Ticket

interface WebGateway {
    fun listFaqs(): Flux<Faq>
    fun voteFaqUp(id: String, email: String, token: String): Mono<Void>
    fun voteFaqDown(id: String, user: String, token : String): Mono<Void>
    fun createTicket(user: String, token : String, domain: Ticket): Mono<Void>
    fun uploadAttachment(user: String, token: String, imgBytes: ByteArray): Mono<Attachment>
    fun findUser(token: String): Mono<UserResponse>
    fun createRequest(user: String, token : String, domain: Request): Mono<Void>
}