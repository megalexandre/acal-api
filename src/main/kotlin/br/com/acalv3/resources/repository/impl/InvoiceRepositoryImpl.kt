package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.resources.model.business.toInvoice
import br.com.acalv3.resources.model.business.toInvoiceEntity
import br.com.acalv3.resources.model.business.toInvoicePage
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.InvoiceRepositoryJpa
import br.com.acalv3.resources.repository.specification.InvoiceSpecification
import java.util.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class InvoiceRepositoryImpl(
    private val repository: InvoiceRepositoryJpa,
) : InvoiceRepository, DefaultRepository {

    override fun getById(id: String): Invoice =
        repository.findByIdOrNull(UUID.fromString(id))?.toInvoice() ?: throw NotFoundException()

    override fun save(invoice: Invoice): Invoice =
        repository.save(invoice.toInvoiceEntity()).toInvoice()

    override fun update(invoice: Invoice): Invoice =
        repository.save(invoice.toInvoiceEntity()).toInvoice()

    override fun paginate(request: InvoicePage): Page<Invoice> =
        repository.findAll(
            InvoiceSpecification(request).getSpecification(),
            super.pageable(request)
        ).toInvoicePage()

    override fun getAll(): List<Invoice> =
        repository.findAll().toInvoice()

}