package br.com.acalv3.integration

import br.com.acalv3.Application
import br.com.acalv3.domain.model.security.UserDomain
import br.com.acalv3.resources.model.security.UserEntity
import br.com.acalv3.resources.repository.interfaces.UserRepositoryJpa
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import io.restassured.http.ContentType.JSON
import io.restassured.http.Header
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import javax.annotation.PostConstruct
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus.OK
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.springframework.util.StreamUtils.copyToString
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@TestConfiguration
@Testcontainers
@ComponentScan(basePackageClasses = [Application::class])
@TestPropertySource("classpath:application.yml")
@SpringBootTest(
	webEnvironment = RANDOM_PORT,
)
abstract class DefaultGatewayTest{

	@Value("classpath:json/request/user_login.json")
	private lateinit var resource: Resource

	@LocalServerPort
	var port: Int = 0

	@Autowired
	lateinit var objectMapper: ObjectMapper

	@Autowired
	lateinit var userRepositoryJpa: UserRepositoryJpa

	final var token: String? = null
	final var header: Header? = null
	final var host: String? = null

	@PostConstruct
	fun init(){
		if (token == null){
			if (userRepositoryJpa.findByUsername("alexandre") == null) {
				userRepositoryJpa.save(
					UserEntity(
						id = UUID.randomUUID(),
						username = "alexandre",
						password = BCryptPasswordEncoder().encode("senha")
					)
				)
			}

			val response = RestAssured.given()
				.contentType(JSON)
				.`when`()
				.body(copyToString(resource.inputStream, defaultCharset()))
				.post("$LOCAL_HOST$port$LOGIN_ROUTER")
				.then()
				.statusCode(OK.value()).extract().asString()

			val userLogin: UserDomain = objectMapper.readValue(response, UserDomain::class.java)
			assertNotNull(userLogin.token, "Must return a valid token")

			this.token = userLogin.token
			this.header = Header(AUTHORIZATION, token)
			this.host = "$LOCAL_HOST$port"
		}
	}

	companion object{
		private const val LOGIN_ROUTER = "/auth/login"
		private const val LOCAL_HOST = "http://localhost:"
		private const val AUTHORIZATION = "Authorization"

		@Container
		private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
			withDatabaseName("acaldb")
			withUsername("root")
			withPassword("123456")
			start()
		}

		@JvmStatic
		@DynamicPropertySource
		fun properties(registry: DynamicPropertyRegistry) {
			registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
			registry.add("spring.datasource.password", mysqlContainer::getPassword)
			registry.add("spring.datasource.username", mysqlContainer::getUsername)
		}

	}


}
