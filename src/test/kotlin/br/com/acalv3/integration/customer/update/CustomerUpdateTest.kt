package br.com.acalv3.integration.customer.update

import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.CustomerEntity
import br.com.acalv3.stub.customerStub
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


class CustomerUpdateTest: DefaultGatewayTest() {

	@Value("classpath:json/request/customer/update/customer_update.json")
	private lateinit var customerUpdate: Resource

	@Autowired
	private lateinit var customerRepository: CustomerRepository

	@Autowired
	lateinit var repository: JpaRepository<CustomerEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should update a customer`(){

		val savedCustomer = customerRepository.save(customerStub())
		val customer = copyToString(customerUpdate.inputStream, defaultCharset())

		Given {
			header(header)
			contentType(JSON)
			body(customer)
		} When {
			put("$host/customer/update")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
			hasKey("name").matches("update")
		}
	}

}
