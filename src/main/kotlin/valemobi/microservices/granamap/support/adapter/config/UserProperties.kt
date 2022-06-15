package valemobi.microservices.granamap.support.adapter.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "api-granamap-user")
class UserProperties {
    lateinit var host: String
}