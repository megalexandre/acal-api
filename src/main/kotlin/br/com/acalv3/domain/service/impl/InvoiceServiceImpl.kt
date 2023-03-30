package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.domain.service.InvoiceService
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class InvoiceServiceImpl(
	val repository: InvoiceRepository,
): InvoiceService {

	override fun getById(id: String): Invoice =
		repository.getById(id)

	override fun save(invoice: Invoice): Invoice =
		repository.save(invoice)

	override fun saveAll(invoice: List<Invoice>): List<Invoice> =
		repository.save(invoice)

	override fun update(invoice: Invoice): Invoice =
		repository.save(invoice)

	override fun paginate(pageRequest: InvoicePage): Page<Invoice> =
		repository.paginate(pageRequest)

	override fun getAll(): List<Invoice> =
		repository.getAll()

	override fun report(): ByteArray? = repository.report()

	override fun report(id: UUID): ByteArray? = repository.report(id)
}
