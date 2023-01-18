package br.com.acalv3.integration.bill

import br.com.acalv3.application.configuration.error.AppAdvice
import br.com.acalv3.integration.DefaultGatewayTest
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class BillPaymentTest: DefaultGatewayTest()  {

	var mckMvc: MockMvc = MockMvcBuilders
		.standaloneSetup()
		.setControllerAdvice(AppAdvice())
		.build()

	@Test
	fun `should realize a bill payment`()  {
	}

}
