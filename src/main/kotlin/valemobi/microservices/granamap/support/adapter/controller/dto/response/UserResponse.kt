package valemobi.microservices.granamap.support.adapter.controller.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.util.*

@Component
data class UserResponse(
    var id: UUID? = null,
    var email: String? = null,
    var name: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    var dateUpdate: OffsetDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    var dateInsert: OffsetDateTime? = null
)