package br.com.acalv3.resources.repository.impl

import br.com.acalv3.commons.DefaultReport
import br.com.acalv3.commons.referenceFormat
import br.com.acalv3.domain.enumeration.Report.INVOICE
import br.com.acalv3.domain.exception.InvoiceNotFoundException
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.resources.model.business.toInvoice
import br.com.acalv3.resources.model.business.toInvoiceEntity
import br.com.acalv3.resources.model.business.toInvoicePage
import br.com.acalv3.resources.model.report.InvoiceReport
import br.com.acalv3.resources.repository.interfaces.InvoiceRepositoryJpa
import br.com.acalv3.resources.repository.specification.InvoiceSpecification
import java.io.File
import java.time.LocalDateTime.now
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.util.ResourceUtils

@Repository
class InvoiceRepositoryImpl(
    private val repository: InvoiceRepositoryJpa,
    private val qualityRepository: QualityRepositoryImpl,
) : InvoiceRepository {

    override fun getById(id: String): Invoice =
        repository.findByIdOrNull(UUID.fromString(id))?.toInvoice() ?: throw NotFoundException()

    override fun payById(id: String) {
        runCatching {
            val invoice = getById(id)
            val newInvoice = invoice.copy(isPayed = true)
            newInvoice.invoiceDetails = invoice.invoiceDetails?.map {
                it.copy(
                    isPayed = true,
                    dataPayed = now()
                )
            }

            save(newInvoice)
        }.onFailure {
            throw InvoiceNotFoundException("invoice not found: $it")
        }
    }

    override fun save(type: Invoice): Invoice =
        repository.save(type.toInvoiceEntity()).toInvoice()

    override fun report(page: InvoicePage): DefaultReport =
        createReport(repository.findAll(InvoiceSpecification(page).getSpecification(), sort(page)).toInvoice())

    override fun report(id: UUID): DefaultReport =
        createReport(listOf(repository.findById(id).map {it.toInvoice()}.orElseThrow()))

    private fun createReport(invoices: List<Invoice>): DefaultReport = run {
        val qualities = qualityRepository.findByReferenceIn(invoices.map { q -> q.reference }.distinct())

        val separator = File.separator
        val path = ResourceUtils.getFile(
            """classpath:report${separator}invoice"""
        ).absolutePath

        DefaultReport(
            dataList =  invoices.map {invoice ->
                InvoiceReport(
                    invoice = invoice,
                    quality = qualities?.firstOrNull{ quality -> quality.reference == invoice.reference },
                )
            },
            report = INVOICE,
            param = hashMapOf(
                "SUBREPORT_DIR" to path,
                "LOGO" to ClassLoader.getSystemResource("acal-logo.jpg").path
            ),
        )
    }


    override fun findByActualReference(): List<Invoice>? =
        repository.findByReference(now().referenceFormat())?.toInvoice()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun count(): Long = repository.count()

    override fun update(invoice: Invoice): Invoice =
        repository.save(invoice.toInvoiceEntity()).toInvoice()

    override fun save(invoice: List<Invoice>): List<Invoice> =
        repository.saveAll(invoice.toInvoiceEntity()).toInvoice()

    override fun paginate(page: InvoicePage): Page<Invoice> =
        repository.findAll(
            InvoiceSpecification(page).getSpecification(),
            super.pageable(page)
        ).toInvoicePage()

    override fun findAll(page: InvoicePage): List<Invoice> =
        repository.findAll().toInvoice()

    override fun saveAll(type: List<Invoice>) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Invoice> =
        repository.findAll().toInvoice()

}
