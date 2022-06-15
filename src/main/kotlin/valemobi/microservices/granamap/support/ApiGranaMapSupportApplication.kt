package valemobi.microservices.granamap.support

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["valemobi"])
class ApiGranaMapSupportApplication

fun main(args: Array<String>) {
	runApplication<ApiGranaMapSupportApplication>(*args)
}
