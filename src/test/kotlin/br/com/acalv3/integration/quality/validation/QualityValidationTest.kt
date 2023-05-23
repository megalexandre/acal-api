package br.com.acalv3.integration.quality.validation

import br.com.acalv3.domain.enumeration.Param.CHLORINE
import br.com.acalv3.domain.enumeration.Param.COLOR
import br.com.acalv3.domain.enumeration.Param.ESCHERICHIA
import br.com.acalv3.domain.enumeration.Param.TOTAL_COLIFORMS
import br.com.acalv3.domain.enumeration.Param.TURBIDITY
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.QualityEntity
import br.com.acalv3.resources.model.business.toEntity
import br.com.acalv3.stub.qualityStub
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.util.StreamUtils.copyToString

class QualityValidationTest: DefaultGatewayTest() {

	@Value("classpath:json/request/quality/request/save/quality-save-request.json")
	private lateinit var requestSave: Resource

	@Value("classpath:json/request/quality/request/update/quality-update-request.json")
	private lateinit var requestUpdate: Resource

	@Autowired
	private lateinit var repository: JpaRepository<QualityEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	private val referenceJan = "012023"
	private val referenceFev = "022023"

	@Test
	fun `should do not save same reference`(){
		repository.save(qualityStub(
			startedAt = referenceJan
		).toEntity())

		val request = copyToString(requestSave.inputStream, defaultCharset())
			.replace("#startedAt", referenceJan)

		Given {
			contentType(JSON)
			header(header)
			body(request)
		} When {
			post("$host/quality")
		} Then {
			statusCode(SC_BAD_REQUEST)
			body("detail", equalTo(ERROR_MESSAGE))
		}
	}

	@Test
	fun `should do not update same reference`(){
		val saved = repository.save(
			qualityStub(
				startedAt = referenceJan
			).toEntity()
		)

		val savedB = repository.save(
			qualityStub(
				startedAt = referenceFev
			).toEntity()
		)

		val request = copyToString(requestUpdate.inputStream, defaultCharset())
			.replace("#startedAt", referenceJan)
			.replace("#id",savedB.id.toString())
			.replace("#ID_COLOR", saved.gathering?.first { it.param === COLOR  }?.id.toString())
			.replace("#ID_TURBIDITY", saved.gathering?.first { it.param === TURBIDITY  }?.id.toString())
			.replace("#ID_CHLORINE", saved.gathering?.first { it.param === CHLORINE  }?.id.toString())
			.replace("#ID_ESCHERICHIA", saved.gathering?.first { it.param === ESCHERICHIA  }?.id.toString())
			.replace("#ID_TOTAL_COLIFORMS", saved.gathering?.first { it.param === TOTAL_COLIFORMS  }?.id.toString())


		Given {
			contentType(JSON)
			header(header)
			body(request)
		} When {
			put("$host/quality/update")
		} Then {
			statusCode(SC_BAD_REQUEST)
			body("detail", equalTo(ERROR_MESSAGE))
		}

	}

	companion object{
		private const val ERROR_MESSAGE = "Já existe um coleta cadastrada para a referência: Janeiro/2023"
	}

}
