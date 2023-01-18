package br.com.acalv3.application.configuration.filter

import br.com.acalv3.application.configuration.dto.UserLogin
import br.com.acalv3.application.configuration.security.TokenAuthenticationService
import br.com.acalv3.domain.model.v3.UserModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter(
	url: String = "/login",
	private val objectMapper: ObjectMapper,
	private val authManager: AuthenticationManager,
	private val tokenAuthenticationService: TokenAuthenticationService,
	) :
	AbstractAuthenticationProcessingFilter(
		AntPathRequestMatcher(url)
	) {
	private var logger: Logger = LoggerFactory.getLogger(JWTLoginFilter::class.java)

	override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

		val credentials: UserLogin = objectMapper.readValue(request?.inputStream, UserLogin::class.java).also {
			logger.info("attempt authentication for user:${it.username}")
		}

		val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
			credentials.username,
			credentials.password,
			credentials.authorities,
		)

		return authManager.authenticate(usernamePasswordAuthenticationToken)
	}

	override fun successfulAuthentication(
		request: HttpServletRequest?,
		response: HttpServletResponse,
		chain: FilterChain?,
		authResult: Authentication
	) {
		logger.info("successful Authentication for user: ${(authResult.principal as UserModel).username}")

		return tokenAuthenticationService.addAuthentication(response, authResult)
	}

}