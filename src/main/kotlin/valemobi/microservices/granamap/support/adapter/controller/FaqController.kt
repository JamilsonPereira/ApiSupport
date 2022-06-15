package valemobi.microservices.granamap.support.adapter.controller


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.response.FaqResponse
import valemobi.microservices.granamap.support.adapter.controller.mapper.FaqMapperDomainAndDTO
import valemobi.microservices.granamap.support.usecase.articles.ListFaqs
import valemobi.microservices.granamap.support.usecase.articles.VoteFaqDown
import valemobi.microservices.granamap.support.usecase.articles.VoteFaqUp

@RestController
@RequestMapping("/faqs")
class FaqController constructor(
    private val listFaqs: ListFaqs,
    private val voteFaqNegative: VoteFaqDown,
    private val voteFaqPositive: VoteFaqUp,
    private val supportMapperDomainAndDTO: FaqMapperDomainAndDTO,


    ) {
    @GetMapping
    fun listFaqs(): Flux<FaqResponse> {
        return listFaqs.execute()
            .map { supportMapperDomainAndDTO.convertListDomainToResponse(it) }
    }

    @PutMapping("/{id}/up")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun faqVoteUp(@PathVariable id: String): Mono<Void> {
        return voteFaqPositive.execute(id)
    }

    @PutMapping("/{id}/down")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun faqVoteDown(@PathVariable id: String): Mono<Void> {
        return voteFaqNegative.execute(id)
    }
}