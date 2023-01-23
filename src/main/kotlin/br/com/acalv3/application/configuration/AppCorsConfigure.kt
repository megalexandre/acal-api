package br.com.acalv3.application.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppCorsConfigure: WebMvcConfigurer {

	override fun addCorsMappings(registry: CorsRegistry) {
		registry
			.addMapping(MAPPING)
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS",  "HEAD", "TRACE", "CONNECT")
	}
	companion object{
		const val MAPPING = "/**"
	}
}