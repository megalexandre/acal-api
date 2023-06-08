package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Reason.CATEGORY
import br.com.acalv3.domain.enumeration.Reason.DUE
import br.com.acalv3.domain.enumeration.Reason.WATER
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.InvoiceDetail
import br.com.acalv3.domain.model.page.InvoicePage
import br.com.acalv3.domain.repository.InvoiceRepository
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.InvoiceService
import br.com.acalv3.domain.service.LinkService
import java.util.UUID
import java.util.UUID.randomUUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class InvoiceServiceImpl(
	val repository: InvoiceRepository,
	val linkService: LinkService,
	val hydrometerService: HydrometerService,
): InvoiceService {

	override fun getById(id: String): Invoice =
		repository.getById(id)

	override fun payById(id: String) =
		repository.payById(id)

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
		}

		return repository.save(invoice)
	}

	override fun update(invoice: Invoice): Invoice =
		repository.save(invoice)

	override fun paginate(pageRequest: InvoicePage): Page<Invoice> =
		repository.paginate(pageRequest)

	override fun getAll(): List<Invoice> =
		repository.getAll()

	override fun report(id: UUID): ByteArray? = repository.report(id)

	override fun findByActualReference(): List<Invoice>? = repository.findByActualReference()
}
