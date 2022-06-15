package valemobi.microservices.granamap.support.adapter.gateway.web.response

import com.fasterxml.jackson.annotation.JsonAlias

class ArticlesDto(
    @JsonAlias("name")
    var id: Long,

    @JsonAlias("name")
    var name: String? = "",

    @JsonAlias("title")
    var title: String? = "",

    @JsonAlias("body")
    var description: String? = "",

    @JsonAlias("created_at")
    var created_at: String? = ""
)
class SupportDto(
    @JsonAlias("articles")
    var articles: MutableList<ArticlesDto> = mutableListOf<ArticlesDto>()
)