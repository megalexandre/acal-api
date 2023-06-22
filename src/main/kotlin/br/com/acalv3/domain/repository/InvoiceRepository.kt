package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.resources.model.report.DefaultReport
import java.util.UUID

interface InvoiceRepository: AbstractRepository<Invoice, InvoicePage> {
    fun update(invoice: Invoice): Invoice
    fun save(invoice: List<Invoice>): List<Invoice>
    fun report(id: UUID): DefaultReport
    fun report(page: InvoicePage): DefaultReport
    fun findByActualReference(): List<Invoice>?
    fun payById(id: String)
}