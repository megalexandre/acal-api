package br.com.acalv3.resources.repository.impl

import br.com.acalv3.commons.ReportUtils
import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.enumeration.Report.BILL
import br.com.acalv3.domain.exception.InvoiceNotFoundException
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.resources.model.business.toInvoice
import br.com.acalv3.resources.model.business.toInvoiceEntity
import br.com.acalv3.resources.model.business.toInvoicePage
import br.com.acalv3.resources.model.report.toReport
import br.com.acalv3.resources.repository.interfaces.InvoiceRepositoryJpa
import br.com.acalv3.resources.repository.specification.InvoiceSpecification
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class InvoiceRepositoryImpl(
    private val repository: InvoiceRepositoryJpa,
    private val qualityRepository: QualityRepositoryImpl
) : InvoiceRepository {

    override fun getById(id: String): Invoice =
        repository.findByIdOrNull(UUID.fromString(id))?.toInvoice() ?: throw NotFoundException()

    override fun payById(id: String) {
        runCatching {
            val invoice = getById(id)

            invoice.invoiceDetails?.forEach {
                it.isPayed = true
                it.dataPayed = LocalDateTime.now()
            }
            save(invoice.copy(isPayed = true))
        }.onFailure {
            throw InvoiceNotFoundException("invoice not found: $it")
        }
    }

    override fun save(type: Invoice): Invoice =
        repository.save(type.toInvoiceEntity()).toInvoice()

    override fun report(id: UUID): ByteArray? = run {
        val data = repository.findByIdOrNull(id)!!
        val report = data.toReport()
        val quality = qualityRepository.findByStartedAt(data.reference)

        with(quality!!.gathering!!){
            with(report){
                color = first { it.param == Param.COLOR }.toReport()
                turbidity = first { it.param == Param.TURBIDITY }.toReport()
                chlorine = first { it.param == Param.CHLORINE }.toReport()
                escherichia = first { it.param == Param.ESCHERICHIA }.toReport()
                totalColiforms = first { it.param == Param.TOTAL_COLIFORMS }.toReport()
            }
        }

        report.params.plus(quality.gathering?.toReport())

        ReportUtils().print(
            data = listOf(report),
            report = BILL,
            param = hashMapOf()
        )
    }

    override fun findByActualReference(): List<Invoice>? = repository.findByReference(
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMYYYY"))
    )?.toInvoice()

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

    override fun getAll(): List<Invoice> = repository.findAll().toInvoice()

    override fun findAll(page: InvoicePage): List<Invoice> =
        repository.findAll().toInvoice()

    override fun findAll(): List<Invoice> =
        repository.findAll().toInvoice()

}
