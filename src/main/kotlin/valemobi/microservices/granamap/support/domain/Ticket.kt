package valemobi.microservices.granamap.support.domain

import valemobi.microservices.granamap.support.domain.enums.ContactReason

class Ticket(
    var subject: String,
    var body: String,
    var contactReason: ContactReason,
    var attachment: String? = null,
    var tags: MutableList<String> = mutableListOf()
)