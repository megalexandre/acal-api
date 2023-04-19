package br.com.acalv3.integration.customer.validation

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
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.util.StreamUtils.copyToString

class CustomerValidationTest: DefaultGatewayTest() {

	@Value("classpath:json/request/customer/validation/customer_post_with_invalid_person_type.json")
	private lateinit var customerInvalidPersonType: Resource

	@Value("classpath:json/request/customer/validation/customer_post_without_document.json")
	private lateinit var customerInvalidDocument: Resource

	@Value("classpath:json/request/customer/validation/customer_post_without_name.json")
	private lateinit var customerInvalidName: Resource

	@Autowired
	lateinit var repository: JpaRepository<CustomerEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should reject customer when name is not present 400`(){
		val customer = copyToString(customerInvalidPersonType.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(customer)
		} When {
			post("$host/customer")
		} Then {
			statusCode(BAD_REQUEST.value())
			body("$", hasKey("error"))
		}
	}

	@Test
	fun `should reject customer when document is not present 400`(){
		val postUser = copyToString(customerInvalidDocument.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(postUser)
		} When {
			post("$host/customer")
		} Then {
			statusCode(BAD_REQUEST.value())
			body("$", hasKey("error"))
		}
	}

	@Test
	fun `should reject customer with invalid person type 400`(){
		val postUser = copyToString(customerInvalidName.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(postUser)
		} When {
			post("$host/customer")
		} Then {
			statusCode(BAD_REQUEST.value())
			body("$", hasKey("error"))
		}
	}
}
