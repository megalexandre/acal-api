package br.com.acalv3.domain.enumeration

enum class Report(val location: String) {
	LINK("report/link.jrxml"),
	INVOICE("report/invoice/invoice.jrxml"),
	BOOK("report/invoice/book.jrxml"),
	BILL("report/bill.jrxml"),
}