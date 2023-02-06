package br.com.acalv3.integration.customer.update

import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.stub.customerStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils.*
import java.nio.charset.Charset.defaultCharset


class CustomerUpdateTest: DefaultGatewayTest() {

	@Value("classpath:json/request/customer/update/customer_update.json")
	private lateinit var customerUpdate: Resource

	@Autowired
	private lateinit var customerRepository: CustomerRepository

	@Test
	fun `should update a customer`(){

		val savedCustomer = customerRepository.save(customerStub())
		val customer = copyToString(customerUpdate.inputStream, defaultCharset())
				.replace("\$id",savedCustomer.id)

		Given {
			header(header)
			contentType(JSON)
			body(customer)
		} When {
			put("$basePath/customer/update")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
			hasKey("name").matches("update")
		}
	}

}
