package br.com.acalv3.integration.customer.save

import br.com.acalv3.integration.DefaultGatewayTest
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.util.StreamUtils.*
import java.nio.charset.Charset.defaultCharset


class CustomerSaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/customer/customer_minimal_field.json")
	private lateinit var customerMinimal: Resource

	@Value("classpath:json/request/customer/customer_completed_field.json")
	private lateinit var customerCompleted: Resource

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
			body(customer)
		} When {
			post("$basePath/customer")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}
	}


	/*
	@Test
	fun `should reject customer when name is not present 400`(){
		val postUser = copyToString(userPostWithName.inputStream, defaultCharset())

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
	fun `should reject customer when document is not present 400`(){
		val postUser = copyToString(userPostWithDocument.inputStream, defaultCharset())

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
		val postUser = copyToString(userPostWithInvalidPersonType.inputStream, defaultCharset())

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
	*/
}
