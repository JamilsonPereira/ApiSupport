package valemobi.microservices.granamap.support.adapter.gateway.mapper

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.gateway.web.response.SupportDto
import valemobi.microservices.granamap.support.domain.Faq

@Component
class FaqMapperDtoDomain {
    fun convertDtoListToDomain(supportDTO: Mono<SupportDto>): Flux<Faq> {
        return supportDTO.flatMapIterable {
            var listFaq: MutableList<Faq> = mutableListOf()
            it.articles.forEach { article ->
                listFaq.add(
                    Faq(
                        id = article.id,
                        title = if (article.title != null) article.title else "",
                        description = if (article.description != null) article.description else ""
                    )
                )
            }
            listFaq
        }

    }
}