package br.com.acalv3.application.configuration.filter

import br.com.acalv3.application.configuration.security.TokenAuthenticationService
import br.com.acalv3.domain.model.security.UserDomain
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalDateTime.now
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class JWTLoginFilter(
	url: String = "/login",
	private val objectMapper: ObjectMapper,
	private val authManager: AuthenticationManager,
	private val tokenAuthenticationService: TokenAuthenticationService,
	): AbstractAuthenticationProcessingFilter(
		AntPathRequestMatcher(url)
	) {

	override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication = run {

		val credentials: UserDomain = objectMapper.readValue(request?.inputStream, UserDomain::class.java).also {
			logger.info("attempt authentication for user:${it.username}")
		}

		val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
			credentials.username,
			credentials.password,
			credentials.authorities,
		)

		authManager.authenticate(usernamePasswordAuthenticationToken)
	}

	override fun successfulAuthentication(
		request: HttpServletRequest?,
		response: HttpServletResponse,
		chain: FilterChain?,
		authResult: Authentication
	) = run {
		logger.info("successful Authentication for user: ${authResult.principal} at: ${now()}")
		tokenAuthenticationService.addAuthentication(response, authResult)
	}

}