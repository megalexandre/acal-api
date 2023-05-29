package br.com.acalv3.integration.hydrometer.filter

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.service.AddressService
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.PlaceService
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.stub.addressStub
import br.com.acalv3.stub.customerStub
import br.com.acalv3.stub.groupStub
import br.com.acalv3.stub.linkStub
import br.com.acalv3.stub.placeStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.math.BigDecimal
import java.util.UUID
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class HydrometerFilterTest: DefaultGatewayTest() {

	@Autowired
	lateinit var hydrometerService: HydrometerService

	@Autowired
	lateinit var linkService: LinkService

	@Autowired
	lateinit var placeService: PlaceService

	@Autowired
	lateinit var groupService: GroupService

	@Autowired
	lateinit var customerService: CustomerService

	@Autowired
	lateinit var addressService: AddressService

	@Test
	fun `should filter by id`(){
		val hydrometer = save()
		Given {
			contentType(JSON)
			header(header)
		} When {
			get("$host/hydrometer/${hydrometer.id}")
		} Then {
			statusCode(200)
			body("id", equalTo(hydrometer.id.toString()))
		}
	}

	@Test
	fun `should paginate`(){
		Given {
			contentType(JSON)
			header(header)
			body("{}")
		} When {
			post("$host/hydrometer/paginate")
		} Then {
			statusCode(200)
			body("totalElements", equalTo(0))
		}
	}


	fun save(): Hydrometer {
		val address = addressService.save(addressStub())
		val place = placeService.save(placeStub(
			address = address,
			addressId = address.id,
		))
		val group = groupService.save(groupStub())
		val customer = customerService.save(customerStub())

		val link = linkService.save(linkStub(
			place = place,
			mailPlace = place,
			group = group,
			customer = customer,
		))

		return hydrometerService.save(Hydrometer(
			id = UUID.randomUUID(),
			reference = "052023",
			costValue = BigDecimal.TEN,
			consumption = 1000L,
			link = link,
		))
	}


}
