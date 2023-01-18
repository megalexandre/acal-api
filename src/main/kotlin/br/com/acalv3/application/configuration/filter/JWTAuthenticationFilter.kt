package br.com.acalv3.application.configuration.filter

import br.com.acalv3.application.configuration.security.TokenAuthenticationService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter(
	private val tokenAuthenticationService: TokenAuthenticationService
	) : GenericFilterBean() {

	override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
		val authentication = tokenAuthenticationService.getAuthentication(request as HttpServletRequest)
		SecurityContextHolder.getContext().authentication = authentication
		chain?.doFilter(request, response)
	}

}