package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Dashboard
import br.com.acalv3.domain.service.DashboardService
import org.springframework.stereotype.Service

@Service
class DashboardServiceImpl(
	val customerServiceImpl: CustomerServiceImpl,
	val linkServiceImpl: LinkServiceImpl,
	val invoiceServiceImpl: InvoiceServiceImpl
): DashboardService {

	override fun get(): Dashboard {

		val invoices = invoiceServiceImpl.findByActualReference()

		return Dashboard(
			totalCustomer = customerServiceImpl.count(),
			totalLink = linkServiceImpl.countActive(),
			awaitingPaymentInvoice = invoices?.count { it.payout }?.toLong() ?: 0,
			generatedInvoice = invoices?.size?.toLong() ?: 0,
		)
	}
}
