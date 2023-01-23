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
		val authorizationHeaderValue: String? = request.getHeader(AUTHORIZATION)

		if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith(BEARER)) {
			val token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length);
			tokenWrapper.token = token
		}
	}

	companion object{
		private const val AUTHORIZATION = "Authorization"
		private const val BEARER = "Bearer"
	}
}

class BearerTokenWrapper {

	var token: String? = ""

}