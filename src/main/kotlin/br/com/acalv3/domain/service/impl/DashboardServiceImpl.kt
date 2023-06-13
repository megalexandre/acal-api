package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Book
import br.com.acalv3.domain.model.Dashboard
import br.com.acalv3.domain.service.BookService
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.DashboardService
import br.com.acalv3.domain.service.InvoiceService
import br.com.acalv3.domain.service.LinkService
import java.math.BigDecimal.ZERO
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class DashboardServiceImpl(
	val customerService: CustomerService,
	val linkService: LinkService,
	val invoiceService: InvoiceService,
	val bookService: BookService,
): DashboardService {

	override fun get(): Dashboard {

		val invoices = invoiceService.findByActualReference()
		val transactions: List<Book>? = bookService.transactionsByDay(LocalDate.now())

		return Dashboard(
			totalCustomer = customerService.count(),
			totalLink = linkService.countActive(),
			awaitingPaymentInvoice = invoices?.count { it.isPayed }?.toLong() ?: 0,
			generatedInvoice = invoices?.size?.toLong() ?: 0,
			qtdTransactionsToday = transactions?.size ?: 0,
			valueTransactionsToday = transactions?.map { it.value }?.sumOf { it } ?: ZERO
		)
	}
}
