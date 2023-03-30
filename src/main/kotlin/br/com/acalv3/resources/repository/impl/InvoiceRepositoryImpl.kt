package br.com.acalv3.resources.repository.impl

import br.com.acalv3.commons.ReportUtils
import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.enumeration.Report.INVOICE
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.resources.model.business.toInvoice
import br.com.acalv3.resources.model.business.toInvoiceEntity
import br.com.acalv3.resources.model.business.toInvoicePage
import br.com.acalv3.resources.model.report.GatheringReport
import br.com.acalv3.resources.model.report.toReport
import br.com.acalv3.resources.repository.interfaces.InvoiceRepositoryJpa
import br.com.acalv3.resources.repository.specification.InvoiceSpecification
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

    override fun save(type: Invoice): Invoice =
        repository.save(type.toInvoiceEntity()).toInvoice()


    override fun report(): ByteArray? = run {
        val quality = qualityRepository.findByStartedAt("032023")
        val data = repository.findAll().toReport()
        data.forEach{ report ->
            report.color = quality!!.gathering!!.first { it.param == Param.COLOR }.toReport()
            report.turbidity = quality.gathering!!.first { it.param == Param.TURBIDITY }.toReport()
            report.chlorine = quality.gathering!!.first { it.param == Param.CHLORINE }.toReport()
            report.escherichia = quality.gathering!!.first { it.param == Param.ESCHERICHIA }.toReport()
            report.totalColiforms = quality.gathering!!.first { it.param == Param.TOTAL_COLIFORMS }.toReport()
        }

        ReportUtils().print(
            data = data,
            report = INVOICE,
            param = hashMapOf()
        )
    }

    override fun report(id: UUID): ByteArray? = run {
        val data = repository.findByIdOrNull(id)!!
        val report = data.toReport()
        val quality = qualityRepository.findByStartedAt(data.reference)

        report.color = quality!!.gathering!!.first { it.param == Param.COLOR }.toReport()
        report.turbidity = quality.gathering!!.first { it.param == Param.TURBIDITY }.toReport()
        report.chlorine = quality.gathering!!.first { it.param == Param.CHLORINE }.toReport()
        report.escherichia = quality.gathering!!.first { it.param == Param.ESCHERICHIA }.toReport()
        report.totalColiforms = quality.gathering!!.first { it.param == Param.TOTAL_COLIFORMS }.toReport()

        ReportUtils().print(
            data = listOf(report),
            report = INVOICE,
            param = hashMapOf()
        )
    }


    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun count(): Long = repository.count()

    override fun update(invoice: Invoice): Invoice =
        repository.save(invoice.toInvoiceEntity()).toInvoice()

    override fun save(invoice: List<Invoice>): List<Invoice> =
        repository.saveAll(invoice.toInvoiceEntity()).toInvoice()

    override fun paginate(request: InvoicePage): Page<Invoice> =
        repository.findAll(
            InvoiceSpecification(request).getSpecification(),
            super.pageable(request)
        ).toInvoicePage()

    override fun getAll(): List<Invoice> = repository.findAll().toInvoice()

    override fun findAll(page: InvoicePage): List<Invoice> =
        repository.findAll().toInvoice()

    override fun findAll(): List<Invoice> =
        repository.findAll().toInvoice()

}
