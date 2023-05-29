package br.com.acalv3.integration.address.update

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.stub.addressStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.util.UUID
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.test.annotation.DirtiesContext

@DirtiesContext
class AddressUpdateTest: DefaultGatewayTest() {

	@Value("classpath:json/request/address/save/address.json")
	private lateinit var addressRequest: Resource

	@Autowired
	private lateinit var repository: JpaRepository<AddressEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should filter address by id`(){
		val savedAddress = repository.save(addressStub().toAddressEntity())

		Given {
			log().all()
			contentType(JSON)
			header(header)
		} When {
			get("$host/address/${savedAddress.id}")
		} Then {
			statusCode(200)
			hasKey("id").matches(savedAddress.id)
			hasKey("name").matches(savedAddress.name)
		}
	}



}
