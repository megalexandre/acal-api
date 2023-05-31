package br.com.acalv3.integration.hydrometer.save

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.HYDROMETER
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.integration.IntegrationTestUtil
import br.com.acalv3.resources.model.business.HydrometerEntity
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.StreamUtils.copyToString

class HydrometerSaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/hydrometer/save/save-work.json")
	private lateinit var hydrometerMinimal: Resource

	@Autowired
	private lateinit var repository: JpaRepository<HydrometerEntity, UUID>

	@Autowired
	private lateinit var  integrationTestUtil: IntegrationTestUtil

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should save a hydrometer ok 200`(){
		val saveLink = integrationTestUtil.saveLink()

		val hydrometer = copyToString(hydrometerMinimal.inputStream, defaultCharset())
			.replace("#link-id",saveLink.id.toString())

		Given {
			contentType(JSON)
			header(header)
			body(hydrometer)
		} When {
			post("$host/$HYDROMETER")
		} Then {
			statusCode(200)
		}
	}


}
