package br.com.acalv3.integration.address.save

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.AddressEntity
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
import org.springframework.test.annotation.DirtiesContext
import org.springframework.util.StreamUtils.copyToString

@DirtiesContext
class AddressSaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/address/save/address.json")
	private lateinit var addressRequest: Resource

	@Autowired
	private lateinit var repository: JpaRepository<AddressEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should save an address`(){
		val address = copyToString(addressRequest.inputStream, defaultCharset())
			.replace("#address-name", "avenida")

		Given {
			contentType(JSON)
			header(header)
			body(address)
		} When {
			post("$host/address")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
			hasKey("name").matches("avenida")
		}
	}

}
