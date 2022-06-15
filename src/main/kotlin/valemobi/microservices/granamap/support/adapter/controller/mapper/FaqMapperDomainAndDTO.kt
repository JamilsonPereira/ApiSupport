package valemobi.microservices.granamap.support.adapter.controller.mapper

import org.springframework.stereotype.Component
import valemobi.microservices.granamap.support.adapter.controller.dto.response.FaqResponse
import valemobi.microservices.granamap.support.domain.Faq

@Component
class FaqMapperDomainAndDTO {

    fun convertListDomainToResponse(domain: Faq): FaqResponse {
        return FaqResponse(
            domain.id,
            domain.title!!,
            domain.description!!
        )
    }
}