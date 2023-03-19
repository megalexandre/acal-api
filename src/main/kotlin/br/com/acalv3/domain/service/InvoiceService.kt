package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import org.springframework.data.domain.Page

interface InvoiceService {
    fun getById(id: String): Invoice
    fun save(invoice: Invoice): Invoice
    fun saveAll(invoice: List<Invoice>): List<Invoice>
    fun update(invoice: Invoice): Invoice
    fun paginate(pageRequest: InvoicePage): Page<Invoice>
    fun getAll(): List<Invoice>
}