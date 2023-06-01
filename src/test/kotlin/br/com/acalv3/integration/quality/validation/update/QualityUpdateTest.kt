package br.com.acalv3.integration.quality.validation.update

import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.QualityEntity
import br.com.acalv3.resources.model.business.toEntity
import br.com.acalv3.stub.qualityStub
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus.SC_SUCCESS
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.StreamUtils

class QualityUpdateTest: DefaultGatewayTest() {

	@Value("classpath:json/request/quality/request/update/quality-update-request.json")
	private lateinit var requestUpdate: Resource

	@Autowired
	private lateinit var repository: JpaRepository<QualityEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should update a quality`() {
		val saved = repository.save(
			qualityStub(
				startedAt = "022023"
			).toEntity()
		)

		val request = StreamUtils.copyToString(requestUpdate.inputStream, Charset.defaultCharset())
			.replace("#startedAt", "012023")
			.replace("#id",saved.id.toString())
			.replace("#ID_COLOR", saved.gathering?.first { it.param === Param.COLOR }?.id.toString())
			.replace("#ID_TURBIDITY", saved.gathering?.first { it.param === Param.TURBIDITY }?.id.toString())
			.replace("#ID_CHLORINE", saved.gathering?.first { it.param === Param.CHLORINE }?.id.toString())
			.replace("#ID_ESCHERICHIA", saved.gathering?.first { it.param === Param.ESCHERICHIA }?.id.toString())
			.replace("#ID_TOTAL_COLIFORMS", saved.gathering?.first { it.param === Param.TOTAL_COLIFORMS }?.id.toString())


		Given {
			contentType(ContentType.JSON)
			header(header)
			body(request)
		} When {
			put("$host/quality/update")
		} Then {
			statusCode(SC_SUCCESS)
		}

	}

}
