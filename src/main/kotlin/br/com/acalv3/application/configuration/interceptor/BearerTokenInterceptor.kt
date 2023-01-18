package br.com.acalv3.application.configuration.interceptor

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class BearerTokenInterceptor(
	private val tokenWrapper: BearerTokenWrapper
): OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val authorizationHeaderValue: String? = request.getHeader("Authorization")

		if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
			val token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length);
			tokenWrapper.token = token
		}
	}

}