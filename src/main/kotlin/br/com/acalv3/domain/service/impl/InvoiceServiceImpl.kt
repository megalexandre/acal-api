package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Reason.ACCOUNT_PAYMENT
import br.com.acalv3.domain.enumeration.Reason.CATEGORY
import br.com.acalv3.domain.enumeration.Reason.DUE
import br.com.acalv3.domain.enumeration.Reason.WATER
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.exception.InvoiceNotFoundException
import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.InvoiceDetail
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.InvoiceService
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.event.BookEvent
import java.math.BigDecimal.ZERO
import java.time.LocalDateTime
import java.util.UUID
import java.util.UUID.randomUUID
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class InvoiceServiceImpl(
	private val repository: InvoiceRepository,
	private val linkService: LinkService,
	private val hydrometerService: HydrometerService,
	private val applicationEventPublisher: ApplicationEventPublisher,
	private val reportService: ReportServiceImpl
): InvoiceService {

	override fun getById(id: String): Invoice =
		repository.getById(id)

	override fun payById(id: String) {
		runCatching {
			val invoice = getById(id)
			val newInvoice = invoice.copy(isPayed = true)
			newInvoice.invoiceDetails = invoice.invoiceDetails?.map {
				it.copy(
					isPayed = true,
					dataPayed = LocalDateTime.now()
				)
			}

			repository.save(newInvoice)
			applicationEventPublisher.publishEvent(
				BookEvent(this, Book(
					id = randomUUID(),
					value = newInvoice.invoiceDetails?.sumOf { it.value } ?: ZERO,
					createdBy = SecurityContextHolder.getContext().authentication.principal.toString(),
					createdAt = LocalDateTime.now(),
					type = Type.IN,
					reason = ACCOUNT_PAYMENT,
				))
			)

		}.onFailure {
			throw InvoiceNotFoundException("invoice not found: $it")
		}
	}

	override fun save(invoice: Invoice): Invoice =
		repository.save(invoice)

	override fun saveAll(invoice: List<Invoice>): List<Invoice>  {

		invoice.forEach{

			val invoiceDetail = mutableListOf<InvoiceDetail>()
			val link = linkService.getById(it.linkId.toString())

			invoiceDetail.add(
				InvoiceDetail(
					id = randomUUID(),
					reason = DUE,
					value = link.group!!.value,
					isPayed = false,
					dataPayed = null,
				)
			)

			invoiceDetail.add(
				InvoiceDetail(
					id = randomUUID(),
					reason = CATEGORY,
					value = link.group.categoryValue,
					isPayed = false,
					dataPayed = null,
				)
			)

			if(link.place?.hasHydrometer == true){
				hydrometerService.getHydrometerByLinkAndReference(linkId = it.linkId, it.reference)?.let { hydrometer ->
					invoiceDetail.add(
						InvoiceDetail(
							id = randomUUID(),
							reason = WATER,
							value = hydrometer.costValue,
							isPayed = false, dataPayed = null,
						)
					)
				}
			}
			it.invoiceDetails = invoiceDetail
			it.value = it.invoiceDetails?.map { d-> d.value }?.fold(ZERO){ acc, e -> acc + e} ?: ZERO
		}

		return repository.save(invoice)
	}

	override fun update(invoice: Invoice): Invoice =
		repository.save(invoice)

	override fun paginate(pageRequest: InvoicePage): Page<Invoice> =
		repository.paginate(pageRequest)

	override fun getAll(): List<Invoice> = repository.findAll()

	override fun report(id: UUID): ByteArray? = reportService.print(repository.report(id))

	override fun report(pageRequest: InvoicePage) = reportService.print(repository.report(pageRequest))

	override fun findByActualReference(): List<Invoice>? = repository.findByActualReference()
}
