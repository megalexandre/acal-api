package br.com.acalv3.integration

import br.com.acalv3.application.configuration.dto.UserLogin
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import io.restassured.http.Header
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.util.StreamUtils.copyToString
import java.nio.charset.Charset
import javax.annotation.PostConstruct

@SpringBootTest(
	webEnvironment = RANDOM_PORT,
)
class DefaultGatewayTest{

	@Value("classpath:json/request/user_login.json")
	private lateinit var resource: Resource

	@LocalServerPort
	var port: Int = 0

	@Autowired
	lateinit var objectMapper: ObjectMapper

	var token: String? = null
	var header: Header? = null
	var basePath: String? = null

	@PostConstruct
	fun sendLoginAndGetToken(){

		val response = RestAssured.given()
			.contentType(JSON)
			.`when`()
			.body(copyToString(resource.inputStream, Charset.defaultCharset()))
			.post("$LOCAL_HOST$port$LOGIN_ROUTER")
			.then()
			.statusCode(OK.value()).extract().asString()

		val userLogin: UserLogin = objectMapper.readValue(response, UserLogin::class.java)
		assertNotNull(userLogin.token, "Must return a valid token")

		this.token = userLogin.token
		this.header = Header(AUTHORIZATION, token)
		this.basePath = "$LOCAL_HOST$port"
	}
	companion object{
		private const val LOGIN_ROUTER = "/auth/login"
		private const val LOCAL_HOST = "http://localhost:"
		private const val AUTHORIZATION = "Authorization"
	}

}
