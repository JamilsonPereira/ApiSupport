package valemobi.microservices.granamap.support.usecase.articles

import reactor.core.publisher.Flux
import valemobi.microservices.granamap.support.domain.Faq

interface ListFaqs {
    fun execute(): Flux<Faq>
}