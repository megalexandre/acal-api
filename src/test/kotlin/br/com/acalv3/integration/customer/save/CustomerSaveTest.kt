package br.com.acalv3.integration.customer.save

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.CustomerEntity
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.StreamUtils.copyToString

class CustomerSaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/customer/save-work/customer_minimal_field.json")
	private lateinit var customerMinimal: Resource

	@Value("classpath:json/request/customer/save-work/customer_completed_field.json")
	private lateinit var customerCompleted: Resource

	@Autowired
	private lateinit var repository: JpaRepository<CustomerEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should save a minimal customer ok 200`(){
		val customer = copyToString(customerMinimal.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(customer)
		} When {
			post("$basePath/customer")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}
	}

	@Test
	fun `should save a completed customer ok 200`(){
		val customer = copyToString(customerCompleted.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(customer).log().all()
		} When {
			post("$basePath/customer")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}

		assertEquals(repository.findAll().size , 1 , "update cant be grown size")
	}

}
