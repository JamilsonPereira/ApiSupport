package valemobi.microservices.granamap.support.adapter.gateway.web.response

import com.fasterxml.jackson.annotation.JsonAlias
import java.util.*
import kotlin.collections.ArrayList

class AttachmentDto {
        @JsonAlias("upload")
        var upload: Upload? = null
    }

    class Upload {
        @JsonAlias("token")
        var token: String? = null
    }

