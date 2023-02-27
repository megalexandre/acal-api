package br.com.acalv3.application.configuration.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppCorsConfigure: WebMvcConfigurer {

	override fun addCorsMappings(registry: CorsRegistry) {
		registry
			.addMapping(MAPPING)
			.maxAge(3600)
			.allowedOrigins("*")
			.exposedHeaders("X-Get-Header")
			.allowedHeaders("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS",  "HEAD", "TRACE", "CONNECT")
	}
	companion object{
		const val MAPPING = "/**"
	}
}