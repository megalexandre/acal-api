package br.com.acalv3.application.configuration.message

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource


@Configuration
class AppMessageConfiguration {
	@Bean
	fun messageSource(): MessageSource {
		val messageSource = ReloadableResourceBundleMessageSource()
		messageSource.setBasenames("classpath:/messages/api_error_messages")
		messageSource.setDefaultEncoding("UTF-8")
		return messageSource
	}
}