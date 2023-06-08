package br.com.acalv3.resources.repository.impl

import br.com.acalv3.resources.model.business.InvoiceEntity
import br.com.acalv3.resources.model.business.toInvoiceEntity
import br.com.acalv3.resources.repository.interfaces.InvoiceRepositoryJpa
import br.com.acalv3.stub.invoiceDetailStub
import br.com.acalv3.stub.invoiceStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

internal class InvoiceRepositoryImplTest {

    private val repository = mockk<InvoiceRepositoryJpa>()
    private val qualityRepository = mockk<QualityRepositoryImpl>()
    private val invoiceRepositoryImpl = InvoiceRepositoryImpl(repository,qualityRepository )

    @Test
    fun payById() {
        val invoice = invoiceStub()
        invoice.invoiceDetails = listOf(invoiceDetailStub())
        val spyInvoice = slot<InvoiceEntity>()

        every {
            repository.findByIdOrNull(any())
        } returns invoice.toInvoiceEntity()

        every {
            repository.save(capture(spyInvoice))
        } returns invoice.toInvoiceEntity()

        invoiceRepositoryImpl.payById(UUID.randomUUID().toString())

        spyInvoice.captured.invoiceDetails?.forEach{
            assertNotNull(it.dataPayed)
            assertTrue(it.isPayed,"must all invoice details are payed" )
        }

    }
}