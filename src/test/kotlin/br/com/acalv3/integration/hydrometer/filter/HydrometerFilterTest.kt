package br.com.acalv3.integration.hydrometer.filter

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.integration.IntegrationTestUtil
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class HydrometerFilterTest(
	val integrationTestUtil: IntegrationTestUtil
): DefaultGatewayTest() {


	@Test
	fun `should filter by id`(){
		val hydrometer = integrationTestUtil.saveHydrometer()
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





}
