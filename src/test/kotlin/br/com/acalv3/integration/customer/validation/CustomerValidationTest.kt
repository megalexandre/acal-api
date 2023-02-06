package br.com.acalv3.integration.customer.validation

import br.com.acalv3.integration.DefaultGatewayTest
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.util.StreamUtils.copyToString
import java.nio.charset.Charset.defaultCharset

class CustomerValidationTest: DefaultGatewayTest() {

	@Value("classpath:json/request/customer/validation/customer_post_with_invalid_person_type.json")
	private lateinit var customerInvalidPersonType: Resource

	@Value("classpath:json/request/customer/validation/customer_post_without_document.json")
	private lateinit var customerInvalidDocument: Resource

	@Value("classpath:json/request/customer/validation/customer_post_without_name.json")
	private lateinit var customerInvalidName: Resource

	@Test
	fun `should reject customer when name is not present 400`(){
		val customer = copyToString(customerInvalidPersonType.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(customer)
		} When {
			post("$basePath/customer")
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
			post("$basePath/customer")
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
			post("$basePath/customer")
		} Then {
			statusCode(BAD_REQUEST.value())
			body("$", hasKey("error"))
		}
	}
}
