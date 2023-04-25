package br.com.acalv3.integration.address.filter

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.CustomerEntity
import br.com.acalv3.resources.model.business.toCustomerEntity
import br.com.acalv3.stub.customerStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasKey
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.StreamUtils.copyToString


class AddressFilterTest: DefaultGatewayTest() {

	@Autowired
	lateinit var repository: JpaRepository<CustomerEntity, UUID>

	@Value("classpath:json/request/customer/filter/customer_name.json")
	private lateinit var customerName: Resource

	@Value("classpath:json/request/customer/filter/customer_document.json")
	private lateinit var customerDocument: Resource

	@Value("classpath:json/request/customer/filter/customer_birthday.json")
	private lateinit var customerBirthday: Resource

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should filter by id`(){
		val customer = repository.save(customerStub().toCustomerEntity())

		Given {
			contentType(JSON)
			header(header)
		} When {
			get("$host/customer/${customer.id}")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}
	}

	@Test
	fun `should paginate by name`(){
		val customer = repository.save(customerStub().toCustomerEntity())
		val customerQuery = copyToString(customerDocument.inputStream, defaultCharset()).replace("_name", customer.name)

		Given {
			contentType(JSON)
			header(header)
			body(customerQuery)
		} When {
			post("$host/customer/paginate")
		} Then {
			statusCode(200)
			body("$", hasKey("content"))
			body("size", not(equalTo("0")))
		}
	}

	@Test
	fun `should paginate by document`(){
		val customer = repository.save(customerStub().toCustomerEntity())
		val customerQuery = copyToString(customerDocument.inputStream, defaultCharset()).replace("_document", customer.document)

		Given {
			contentType(JSON)
			header(header)
		} When {
			body(customerQuery)
			post("$host/customer/paginate")
		} Then {
			statusCode(200)
			body("$", hasKey("content"))
			body("size", not(equalTo("0")))
		}
	}

	@Test
	fun `should paginate by birthday`(){
		val customer = repository.save(customerStub().toCustomerEntity())
		val customerQuery = copyToString(customerName.inputStream, defaultCharset()).replace("_birthday", customer.birthDay.toString())

		Given {
			contentType(JSON)
			header(header)
		} When {
			body(customerQuery)
			post("$host/customer/paginate")
		} Then {
			statusCode(200)
			body("$", hasKey("content"))
			body("size", not(equalTo("0")))
		}
	}

}
