package br.com.acalv3.domain.model

import br.com.acalv3.stub.invoiceStub
import java.time.LocalDateTime.now
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class InvoiceTest{

    @Test
    fun `one mouth after due date account overdue must be true`(){
        val invoice = invoiceStub(
            isPayed = false,
            emission = now(),
            dueDate = now().plusMonths(1)
        )

        assertTrue(invoice.accountOverdue)
    }

    @Test
    fun `when the mouth is no completed due date account overdue must be false`(){
        val invoice = invoiceStub(
            isPayed = false,
            emission = now(),
            dueDate = now().plusMonths(1).minusDays(1)
        )

        assertFalse(invoice.accountOverdue)
    }

}