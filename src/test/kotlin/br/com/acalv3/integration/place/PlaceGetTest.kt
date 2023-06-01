package br.com.acalv3.integration.place

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.AddressEntity
import br.com.acalv3.resources.model.business.PlaceEntity
import br.com.acalv3.resources.model.business.toAddress
import br.com.acalv3.resources.model.business.toAddressEntity
import br.com.acalv3.resources.model.business.toPlaceEntity
import br.com.acalv3.stub.addressStub
import br.com.acalv3.stub.placeStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.util.UUID
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository

class PlaceGetTest: DefaultGatewayTest() {

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
		val place = repository.save(placeStub(
			address = address.toAddress(),
			addressId = address.id
		).toPlaceEntity())


		Given {
			contentType(JSON)
			header(header)
		} When {
			get("$host/place/${place.id}")
		} Then {
			statusCode(200)
			body("letter", Matchers.equalTo("A"))
			body("number", Matchers.equalTo(1))
		}
	}

}
