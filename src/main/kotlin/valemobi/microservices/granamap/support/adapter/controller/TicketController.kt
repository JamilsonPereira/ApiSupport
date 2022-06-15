package valemobi.microservices.granamap.support.adapter.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.request.TicketRequest
import valemobi.microservices.granamap.support.adapter.controller.dto.response.UserResponse
import valemobi.microservices.granamap.support.adapter.controller.mapper.RequestMapperDomainAndDto
import valemobi.microservices.granamap.support.adapter.controller.mapper.TicketMapperDomainAndDto
import valemobi.microservices.granamap.support.adapter.gateway.mapper.RequestMapperDtoDomain
import valemobi.microservices.granamap.support.adapter.gateway.web.WebGateway
import valemobi.microservices.granamap.support.usecase.ticket.CreateRequest
import valemobi.microservices.granamap.support.usecase.ticket.CreateTicket


@RestController
@RequestMapping("/tickets")
class TicketController
    (
    private val createTicket: CreateTicket,
    private val ticketMapperDomainAndDto: TicketMapperDomainAndDto,
    private val createRequest: CreateRequest,
    private val requestMapperDomainAndDto: RequestMapperDomainAndDto
) {
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun createTicket(
        @RequestBody ticket: TicketRequest,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String
    ): Mono<Void> {
        return createTicket.execute(
            ticketMapperDomainAndDto
                .convertRequestToDomain(ticket),
            token
        )
    }

    @PostMapping("/request")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun createRequest(
        @RequestBody request: TicketRequest,
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String
    ): Mono<Void> {
        return createRequest.execute(
            requestMapperDomainAndDto
                .convertRequestToDomain(request),
            token
        )
    }
}