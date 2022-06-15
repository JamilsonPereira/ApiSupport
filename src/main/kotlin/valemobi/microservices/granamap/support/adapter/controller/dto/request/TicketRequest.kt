package valemobi.microservices.granamap.support.adapter.controller.dto.request

import valemobi.microservices.granamap.support.domain.enums.ContactReason

class TicketRequest(
    var subject: String,
    var body: String,
    var contactReason: ContactReason,
    var attachment: String? = null
)