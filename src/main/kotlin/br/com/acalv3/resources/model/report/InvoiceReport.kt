package br.com.acalv3.resources.model.report

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.commons.reference
import br.com.acalv3.domain.enumeration.Param
import br.com.acalv3.domain.enumeration.Param.*
import br.com.acalv3.resources.model.business.InvoiceEntity
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter.ofPattern

class InvoiceReport(
    val id: String,
    val address: String,
    val number: String,
    val category: String,
    val customer: String,
    val group: String,
    val reference: String,
    val currentDate: String,
    val dueDate: String,
){
    var color: GatheringReport = GatheringReport(COLOR.name)
    var turbidity: GatheringReport = GatheringReport(TURBIDITY.name)
    var chlorine: GatheringReport = GatheringReport(CHLORINE.name)
    var escherichia: GatheringReport = GatheringReport(ESCHERICHIA.name)
    var totalColiforms: GatheringReport = GatheringReport(TOTAL_COLIFORMS.name)
}

fun InvoiceEntity.toReport() = InvoiceReport(
    id = id.toString(),
    address = link.place.address.name,
    number = """${link.place.number} ${link.place.letter}""",
    category = link.group.category.value,
    customer = link.customer.name,
    group = link.group.name,
    reference = reference.reference(),
    currentDate = now().format(ofPattern(DATE_FORMAT)),
    dueDate = now().format(ofPattern(DATE_FORMAT)),
)

fun List<InvoiceEntity>.toReport() = map { it.toReport() }