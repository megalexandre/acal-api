package br.com.acalv3.integration

import br.com.acalv3.application.configuration.error.AppAdvice
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest(
	webEnvironment = RANDOM_PORT,
)
@AutoConfigureMockMvc
abstract class DefaultGatewayTest(
	val router: String? = null
	){

	@Autowired
	lateinit var objectMapper: ObjectMapper


}
