package br.com.acalv3.integration

import br.com.acalv3.application.configuration.dto.UserLogin
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.io.Resource
import javax.annotation.PostConstruct

@SpringBootTest(
	webEnvironment = RANDOM_PORT,
)
class DefaultGatewayTest(){

	@LocalServerPort
	var port: Int = 0

	@Autowired
	lateinit var objectMapper: ObjectMapper

	var token: String? = null

	@PostConstruct
	fun sendLoginAndGetToken(){

		val response = RestAssured.given()
			.contentType(JSON)
			.`when`()
			.body("{\"username\": \"alexandre\",\"password\": \"senha\"}")
			.post("http://localhost:$port/auth/login")
			.then()
			.statusCode(200).extract().asString()

		val userLogin: UserLogin = objectMapper.readValue(response, UserLogin::class.java)
		Assertions.assertNotNull(userLogin.token, "Must return a valid token")

		this.token = userLogin.token
	}
	companion object{
		lateinit var requestSpecification: RequestSpecification
		private const val DEFAULT_BASE_PATH = "http://localhost"
	}

}
