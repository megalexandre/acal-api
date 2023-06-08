package br.com.acalv3.integration.invoice

import br.com.acalv3.integration.DefaultGatewayTest
import br.com.acalv3.resources.model.business.InvoiceEntity
import br.com.acalv3.resources.model.business.toInvoice
import br.com.acalv3.resources.model.business.toInvoiceEntity
import br.com.acalv3.stub.invoiceDetailStub
import br.com.acalv3.stub.invoiceStub
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository

class InvoiceSaveTest: DefaultGatewayTest() {

	@Autowired
	private lateinit var repository: JpaRepository<InvoiceEntity, UUID>

	@BeforeEach
	fun beforeEach(){
		repository.deleteAll()
	}

	@Test
	fun `should pay by id`(){
		val id = UUID.randomUUID()
		val invoice = invoiceStub(id = id)
		invoice.invoiceDetails = listOf(invoiceDetailStub(
			isPayed = false,
			dataPayed = null,
		))

		repository.save(invoice.toInvoiceEntity())

		Given {
			contentType(JSON)
			header(header)
			body("{}")
		} When {
			patch("$host/invoice/pay/$id")
		} Then {
			statusCode(200)
		}

		val invoiceSaved = repository.findById(invoice.id).get().toInvoice()

		assertTrue(invoiceSaved.isPayed)
		invoiceSaved.invoiceDetails?.forEach{
			assertTrue(it.isPayed)
			assertNotNull(it.dataPayed)
		}
	}

	@Test
	fun `should return bad request when invoice not exists`(){
		val id = UUID.randomUUID()

		Given {
			contentType(JSON)
			header(header)
			body("{}")
		} When {
			patch("$host/invoice/pay/$id")
		} Then {
			statusCode(400)
		}

	}


}
