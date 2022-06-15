package valemobi.microservices.granamap.support.usecase.articles

import reactor.core.publisher.Mono

interface VoteFaqUp{
fun execute(id: String): Mono<Void>
}