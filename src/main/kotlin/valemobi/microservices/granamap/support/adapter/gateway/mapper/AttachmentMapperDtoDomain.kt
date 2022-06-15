package valemobi.microservices.granamap.support.adapter.gateway.mapper

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import valemobi.microservices.granamap.support.adapter.gateway.web.response.AttachmentDto
import valemobi.microservices.granamap.support.domain.Attachment

@Component
class AttachmentMapperDtoDomain() {
    fun convertDtoToDomain(attachmentDto: Mono<AttachmentDto>): Mono<Attachment> {
        return attachmentDto.map {
            Attachment(
                it.upload!!.token!!
            )
        }
    }
}