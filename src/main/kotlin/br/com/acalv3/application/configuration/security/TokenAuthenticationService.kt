package br.com.acalv3.application.configuration.security

import br.com.acalv3.application.configuration.dto.UserLogin
import br.com.acalv3.resources.model.security.UserModel
import br.com.acalv3.resources.model.security.toUserLogin
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.xml.bind.DatatypeConverter

class TokenAuthenticationService(
	private var objectMapper: ObjectMapper
	){
	fun addAuthentication(response: HttpServletResponse, auth: Authentication) {
		val userLogin: UserLogin =(auth.principal as UserModel).toUserLogin()

		val jwt = Jwts.builder()

			.setSubject(auth.name)
				.claim("role", userLogin.authorities?.map { it.name })
				.claim("name", auth.name)
			.setExpiration(
				Date.from(LocalDateTime.now()
						.plusHours(HOURS_TO_EXPIRATION)
						.atZone(ZoneId.systemDefault()).toInstant())
			).signWith(
				SignatureAlgorithm.HS512,
				SecretKeySpec(
					DatatypeConverter.parseBase64Binary(SECRET),
					SignatureAlgorithm.HS512.jcaName
				)
			)

		val token = jwt.compact()
		userLogin.token = token

		val stringResponse = objectMapper.writeValueAsString(UserLogin(
			username = userLogin.username,
			token = userLogin.token,
			authorities = userLogin.authorities
			)
		)

		try {
			response.writer.write(stringResponse)
			response.writer.flush()
			response.writer.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	@Throws(ExpiredJwtException::class)
	fun getAuthentication(request: HttpServletRequest): Authentication? {
		val token = request.getHeader(HEADER_STRING)
		try {
			if (token != null) {
				val user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.body
					.subject
				if (user != null) {
					return UsernamePasswordAuthenticationToken(user, null, Collections.emptyList())
				}
				return null
			}
		} catch (e: IllegalArgumentException ) {
			throw RuntimeException(ERROR_MESSAGE)
		} catch (e: ExpiredJwtException) {
			throw RuntimeException(ERROR_MESSAGE)
		}

		return null
	}

	companion object{
		private const val ERROR_MESSAGE = "Você não possui permissão"
		private const val HOURS_TO_EXPIRATION: Long = 4
		private const val SECRET = "2a108Imja6xTLcqhRUDVrVusTuW7xVtjifhrJon1bDyBkdRzuLSHBVY2q"
		private const val TOKEN_PREFIX = "Bearer"
		private const val HEADER_STRING = "Authorization"
	}

}