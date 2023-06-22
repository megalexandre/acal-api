package br.com.acalv3.resources.model.report

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_REPORT
import br.com.acalv3.application.comunication.Fixture.Companion.DATE_TIME_REPORT
import br.com.acalv3.commons.defaultDateFormat
import br.com.acalv3.commons.defaultFormat
import br.com.acalv3.commons.reference
import br.com.acalv3.commons.toCurrency
import br.com.acalv3.domain.enumeration.Param.CHLORINE
import br.com.acalv3.domain.enumeration.Param.COLOR
import br.com.acalv3.domain.enumeration.Param.ESCHERICHIA
import br.com.acalv3.domain.enumeration.Param.TOTAL_COLIFORMS
import br.com.acalv3.domain.enumeration.Param.TURBIDITY
import br.com.acalv3.domain.enumeration.Reason.CATEGORY
import br.com.acalv3.domain.enumeration.Reason.DUE
import br.com.acalv3.domain.enumeration.Reason.WATER
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.model.Quality
import java.math.BigDecimal.ZERO
import java.time.LocalDateTime.now


class InvoiceReport (
    val invoice: Invoice,
    val quality: Quality?,
){
    val id: String = invoice.id.toString()
    val address: String = invoice.link?.place?.address?.name ?: ""
    val number: String = invoice.link?.place?.number.toString()
    val category: String = invoice.link?.group?.category?.value ?: ""
    val customer = invoice.link?.customer?.name ?: ""
    val group: String = invoice.link?.group?.name ?: ""
    val reference: String = invoice.reference.reference()
    val dueDate: String = invoice.dueDate.defaultDateFormat(DATE_REPORT)
    val emission: String = invoice.emission.defaultFormat(DATE_TIME_REPORT)
    val currentDate: String = now().defaultFormat(DATE_TIME_REPORT)

    val groupValue: String = (invoice.invoiceDetails?.find { it.reason == DUE }?.value).toCurrency()
    val categoryValue: String = (invoice.invoiceDetails?.find { it.reason == CATEGORY }?.value).toCurrency()
    val hydrometerValue: String = (invoice.invoiceDetails?.find { it.reason == WATER }?.value).toCurrency()
    val otherValue: String = ZERO.toCurrency()
    val totalValue: String = (invoice.invoiceDetails?.map { it.value }?.sumOf { it }).toCurrency()

    val param: List<GatheringReport>? = quality?.gathering?.toReport()

    val color: GatheringReport = GatheringReport(COLOR.name)
    val turbidity: GatheringReport = GatheringReport(TURBIDITY.name)
    val chlorine: GatheringReport = GatheringReport(CHLORINE.name)
    val escherichia: GatheringReport = GatheringReport(ESCHERICHIA.name)
    val totalColiforms: GatheringReport = GatheringReport(TOTAL_COLIFORMS.name)
}