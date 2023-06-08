package br.com.acalv3.integration.group.save

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.GROUP
import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.GroupEntity
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.nio.charset.Charset.defaultCharset
import java.util.UUID
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.test.annotation.DirtiesContext
import org.springframework.util.StreamUtils.copyToString

@DirtiesContext
class GroupSaveTest: DefaultGatewayTest() {

	@Value("classpath:json/request/group/save/save-work.json")
	private lateinit var groupRequest: Resource

	@Autowired
	private lateinit var repository: JpaRepository<GroupEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should save an group`(){
		val group = copyToString(groupRequest.inputStream, defaultCharset())

		Given {
			contentType(JSON)
			header(header)
			body(group)
		} When {
			post("$host/$GROUP")
		} Then {
			statusCode(200)
			body("$", hasKey("id"))
		}
	}

}
