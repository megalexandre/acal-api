package br.com.acalv3.application.configuration.security

import br.com.acalv3.application.configuration.filter.JWTAuthenticationFilter
import br.com.acalv3.application.configuration.filter.JWTLoginFilter
import br.com.acalv3.application.configuration.handler.CustomAccessDeniedHandler
import br.com.acalv3.domain.service.v3.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
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
			.antMatchers("/").permitAll()
			.antMatchers(HEALTH_STATUS).permitAll()
			.antMatchers(LOGIN_ROUTER).permitAll()
			.antMatchers(REGISTER_ROUTER).permitAll()
			.anyRequest().authenticated().and()
			.exceptionHandling().accessDeniedHandler( CustomAccessDeniedHandler())
			.and()
			.addFilterBefore(
				JWTLoginFilter(
					url = LOGIN_ROUTER,
					objectMapper = objectMapper,
					authManager = authenticationManagerBean(),
					tokenAuthenticationService = tokenAuthenticationService,
				),
				UsernamePasswordAuthenticationFilter::class.java
			)
			.addFilterBefore(
				JWTAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter::class.java
			)
			.logout()
				.logoutRequestMatcher(AntPathRequestMatcher(LOGOUT_ROUTER, HttpMethod.POST.name))
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
		const val REGISTER_ROUTER = "/auth/register"
		const val HEALTH_STATUS = "/health/status"
		const val LOGIN_ROUTER = "/auth/login"
		const val LOGOUT_ROUTER = "/auth/logout"
	}

}