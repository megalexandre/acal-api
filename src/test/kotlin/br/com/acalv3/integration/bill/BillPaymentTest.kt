package br.com.acalv3.integration.bill

import br.com.acalv3.integration.DefaultGatewayTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import io.restassured.http.Header
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test


class BillPaymentTest: DefaultGatewayTest() {

	@Test
	fun test(){
		given()
			.contentType(JSON)
			.`when`().get("http://localhost:$port/health/status")
			.then()
			.statusCode(200)
	}

	@Test
	fun `should payment response ok 200`(){
		given()
			.contentType(JSON)
			.header(Header("Authorization", token))
			.body("{\"id\": 10}")
			.`when`().post("http://localhost:$port/bill/payment")
			.then()
			.statusCode(200)
			.body("status", equalTo("ok"))
	}

}
