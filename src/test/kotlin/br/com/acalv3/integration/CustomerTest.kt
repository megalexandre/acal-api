package br.com.acalv3.integration

import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.util.StreamUtils.*
import java.nio.charset.Charset
import java.nio.charset.Charset.defaultCharset


class CustomerTest: DefaultGatewayTest() {

	@Value("classpath:json/request/user_post.json")
	private lateinit var validUser: Resource

	@Value("classpath:json/request/user_post_without_document.json")
	private lateinit var userPostWithDocument: Resource

	@Value("classpath:json/request/user_post_without_name.json")
	private lateinit var userPostWithName: Resource

	@Value("classpath:json/request/user_post_with_invalid_person_type.json")
	private lateinit var userPostWithInvalidPersonType: Resource


	@Test
	fun `should save a customer ok 200`(){
		val postUser = copyToString(validUser.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(postUser)
		} When {
			post("$basePath/customer")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}
	}

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


}
