package br.com.acalv3.integration.quality.save

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.CustomerEntity
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.StreamUtils.copyToString

class QualitySaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/quality/save-work/quality-request.json")
	private lateinit var request: Resource

	@Autowired
	private lateinit var repository: JpaRepository<CustomerEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should save a minimal quality ok 200`(){
		val request = copyToString(request.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(request)
		} When {
			post("$host/quality")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}
	}

}
