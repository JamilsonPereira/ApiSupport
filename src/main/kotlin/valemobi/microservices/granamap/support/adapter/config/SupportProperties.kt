package valemobi.microservices.granamap.support.adapter.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "api-zendesk")
class SupportProperties {
    lateinit var host: String
    lateinit var username : String
    lateinit var token : String
    lateinit var tags: MutableList<String>

}