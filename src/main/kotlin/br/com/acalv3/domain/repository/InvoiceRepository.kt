package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import org.springframework.data.domain.Page

interface InvoiceRepository {
    fun getById(id: String): Invoice
    fun save(invoice: Invoice): Invoice
    fun update(invoice: Invoice): Invoice
    fun paginate(request: InvoicePage): Page<Invoice>
    fun getAll(): List<Invoice>
}