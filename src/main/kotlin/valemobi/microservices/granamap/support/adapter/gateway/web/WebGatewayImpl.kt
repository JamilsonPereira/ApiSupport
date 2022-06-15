package valemobi.microservices.granamap.support.adapter.gateway.web

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import valemobi.microservices.granamap.support.adapter.config.SupportProperties
import valemobi.microservices.granamap.support.adapter.config.UserProperties
import valemobi.microservices.granamap.support.adapter.controller.dto.response.UserResponse
import valemobi.microservices.granamap.support.adapter.gateway.mapper.AttachmentMapperDtoDomain
import valemobi.microservices.granamap.support.adapter.gateway.mapper.FaqMapperDtoDomain
import valemobi.microservices.granamap.support.adapter.gateway.mapper.RequestMapperDtoDomain
import valemobi.microservices.granamap.support.adapter.gateway.mapper.TicketMapperDtoDomain
import valemobi.microservices.granamap.support.adapter.gateway.web.response.*
import valemobi.microservices.granamap.support.domain.Attachment
import valemobi.microservices.granamap.support.domain.Faq
import valemobi.microservices.granamap.support.domain.Request
import valemobi.microservices.granamap.support.domain.Ticket
import java.time.Duration

@Service
class WebGatewayImpl(
    private val webClient: WebClient,
    private val supportProperties: SupportProperties,
    private val supportMapperDtoDomain: FaqMapperDtoDomain,
    private val userProperties: UserProperties,
    private val attachmentMapperDtoDomain: AttachmentMapperDtoDomain,
    private val ticketMapperDtoDomain: TicketMapperDtoDomain,
    private val requestMapperDtoDomain: RequestMapperDtoDomain
) : WebGateway {
    override fun listFaqs(): Flux<Faq> {

        var resultZendeskFaq = webClient.mutate()
            .baseUrl("${supportProperties.host}/help_center/pt-br/articles")
            .build()
            .get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(SupportDto::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
        return supportMapperDtoDomain.convertDtoListToDomain(resultZendeskFaq)

    }

    override fun voteFaqUp(id: String, user: String, token: String): Mono<Void> {

        return webClient.mutate()
            .baseUrl("${supportProperties.host}/help_center/pt-br/articles/${id}/up")
            .filter(ExchangeFilterFunctions.basicAuthentication(user, token))
            .build()
            .post()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Void::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }

    override fun voteFaqDown(id: String, user: String, token: String): Mono<Void> {
        return webClient.mutate()
            .baseUrl("${supportProperties.host}/help_center/pt-br/articles/${id}/down")
            .filter(ExchangeFilterFunctions.basicAuthentication(user, token))
            .build()
            .post()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Void::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }

    override fun createTicket(user: String, token: String, domain: Ticket): Mono<Void> {
        var ticketDto = ticketMapperDtoDomain.convertDomainToDto(domain)

        return webClient.mutate()
            .baseUrl("${supportProperties.host}/tickets")
            .filter(ExchangeFilterFunctions.basicAuthentication(user, token))
            .build()
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(
                Mono.just(
                    ticketDto
                ), TicketDto::class.java
            )
            .retrieve()
            .bodyToMono(Void::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }

    override fun uploadAttachment(user: String, token: String, imgBytes: ByteArray): Mono<Attachment> {


        var resultTokenAttachment = webClient.mutate()
            .baseUrl("${supportProperties.host}/uploads?filename=granamap.png")
            .filter(ExchangeFilterFunctions.basicAuthentication(user, token))
            .build()
            .post()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(imgBytes)
            .retrieve()
            .bodyToMono(AttachmentDto::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
        return attachmentMapperDtoDomain.convertDtoToDomain(resultTokenAttachment)
    }

    override fun findUser(token: String): Mono<UserResponse> {
        return   webClient.mutate()
            .baseUrl("${userProperties.host}/users/")
            .defaultHeader(HttpHeaders.AUTHORIZATION, token)
            .build()
            .get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(UserResponse::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }

    override fun createRequest(user: String, token: String, domain: Request): Mono<Void> {
        var requestDto = requestMapperDtoDomain.convertDomainToDto(domain)

        return webClient.mutate()
            .baseUrl("${supportProperties.host}/requests")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "jamilson.nunesp@hotmail.com", token)
            .build()
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(
                Mono.just(
                    requestDto
                ), RequestDto::class.java
            )
            .retrieve()
            .bodyToMono(Void::class.java)
            .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
    }
}