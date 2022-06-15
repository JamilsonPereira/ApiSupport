package valemobi.microservices.granamap.support.usecase.articles.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import valemobi.microservices.granamap.support.adapter.gateway.web.WebGateway
import valemobi.microservices.granamap.support.domain.Faq
import valemobi.microservices.granamap.support.usecase.articles.ListFaqs

@Service
class ListFaqsImpl(
    val webGateway: WebGateway,
) : ListFaqs {
    override fun execute(): Flux<Faq> {
        return Flux.from(webGateway.listFaqs())
    }
}