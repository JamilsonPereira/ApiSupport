package valemobi.microservices.granamap.support.adapter.controller.mapper

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.controller.dto.response.AttachmentResponse

@Component
class AttachmentMapperDomainAndDto {

    fun convertDomainToResponse(domain: Mono<AttachmentResponse>) : Mono<AttachmentResponse>{
        return domain.map {
            AttachmentResponse(
                it.token
            )
        }
    }
}