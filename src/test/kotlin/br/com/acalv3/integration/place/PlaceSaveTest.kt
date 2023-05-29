package br.com.acalv3.integration.place

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.PlaceEntity
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.stub.addressStub
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

class PlaceSaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/place/save/place.json")
	private lateinit var placeRequest: Resource

	@Autowired
	private lateinit var repository: JpaRepository<PlaceEntity, UUID>

	@Autowired
	private lateinit var addressRepository: JpaRepository<AddressEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should save a place`(){
		val address = addressRepository.save(addressStub().toAddressEntity())

		val place = copyToString(placeRequest.inputStream, defaultCharset())
			.replace("#address-id", address.id.toString())

		Given {
			contentType(JSON)
			header(header)
			body(place)
		} When {
			post("$host/place")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
			hasKey("name").matches("avenida")
		}
	}

}
