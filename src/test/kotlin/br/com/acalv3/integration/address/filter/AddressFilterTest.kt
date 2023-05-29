package br.com.acalv3.integration.address.filter

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.stub.addressStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset
import java.util.UUID
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.test.annotation.DirtiesContext
import org.springframework.util.StreamUtils

@DirtiesContext
class AddressFilterTest: DefaultGatewayTest() {

	@Autowired
	lateinit var repository: JpaRepository<AddressEntity, UUID>

	@Value("classpath:json/request/address/filter/address_name.json")
	private lateinit var addressJson: Resource


	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should filter by id`(){
		val address = repository.save(addressStub().toAddressEntity())
		Given {
			contentType(JSON)
			header(header)
		} When {
			get("$host/address/${address.id}")
		} Then {
			statusCode(200)
		}
	}


	@Test
	fun `should paginate by name`(){
		repository.save(addressStub(name = "rua das laranjeiras").toAddressEntity())
		val addressQuery = StreamUtils.copyToString(addressJson.inputStream, Charset.defaultCharset())
			.replace("_name", "rua das laranjeiras")

		Given {
			contentType(JSON)
			header(header)
			body(addressQuery)
		} When {
			post("$host/address/paginate")
		} Then {
			statusCode(200)
			body("$", hasKey("content"))
			body("content.size", equalTo(1))
		}
	}

}
