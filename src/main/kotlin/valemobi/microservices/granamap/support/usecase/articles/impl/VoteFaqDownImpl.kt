package valemobi.microservices.granamap.support.usecase.articles.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.config.SupportProperties
import valemobi.microservices.granamap.support.adapter.gateway.web.WebGateway
import valemobi.microservices.granamap.support.usecase.articles.VoteFaqDown

@Service
class VoteFaqDownImpl(
    private val webGateway: WebGateway,
    private val supportProperties: SupportProperties
): VoteFaqDown {
    override fun execute(id: String): Mono<Void> {
        return webGateway.voteFaqDown(id, supportProperties.username, supportProperties.token)
    }
}