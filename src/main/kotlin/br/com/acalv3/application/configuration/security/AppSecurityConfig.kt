package br.com.acalv3.application.configuration.security

import br.com.acalv3.application.configuration.filter.JWTAuthenticationFilter
import br.com.acalv3.application.configuration.filter.JWTLoginFilter
import br.com.acalv3.application.configuration.interceptor.CustomAccessDeniedHandler
import br.com.acalv3.domain.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.POST
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
@EnableWebSecurity
class AppSecurityConfig(
	private val userService: UserService,
	private val objectMapper: ObjectMapper,
) : WebSecurityConfigurerAdapter() {

	override fun configure(http: HttpSecurity) {
		val tokenAuthenticationService = TokenAuthenticationService(objectMapper)

		http
			.csrf().disable()
			.cors().and()
			.authorizeRequests()
			.antMatchers(HEALTH_STATUS).permitAll()
			.antMatchers(LEGACY).permitAll()
			.antMatchers(PERSON).permitAll()
			.antMatchers(AUTH_LOGIN_ROUTER).permitAll()
			.antMatchers(AUTH_REGISTER_ROUTER).permitAll()
			.anyRequest().authenticated().and()
			.exceptionHandling().accessDeniedHandler( CustomAccessDeniedHandler())
			.and()
			.addFilterBefore(
				JWTLoginFilter(
					url = AUTH_LOGIN_ROUTER,
					objectMapper = objectMapper,
					authManager = authenticationManagerBean(),
					tokenAuthenticationService = tokenAuthenticationService,
				),
				UsernamePasswordAuthenticationFilter::class.java
			)
			.addFilterBefore(
				JWTAuthenticationFilter(tokenAuthenticationService),
				UsernamePasswordAuthenticationFilter::class.java
			)
			.logout()
				.logoutRequestMatcher(AntPathRequestMatcher(AUTH_LOGOUT_ROUTER, POST.name))
	}

	override fun configure(auth: AuthenticationManagerBuilder) {
		auth
			.userDetailsService<UserDetailsService>(userService)
			.passwordEncoder(passwordEncoder())
	}

	@Bean
	override fun authenticationManagerBean(): AuthenticationManager =
		super.authenticationManagerBean()

	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder =
		 BCryptPasswordEncoder()

	@Bean
	fun accessDeniedHandler(): AccessDeniedHandler =
		CustomAccessDeniedHandler()

	companion object {
		const val HEALTH_STATUS = "/health/status"
		const val AUTH_REGISTER_ROUTER = "/auth/register"
		const val AUTH_LOGIN_ROUTER = "/auth/login"
		const val AUTH_LOGOUT_ROUTER = "/auth/logout"
		const val LEGACY = "/legacy**"
		const val PERSON = "/person**"
	}

}