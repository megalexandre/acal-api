package br.com.acalv3.resources.model.report

import br.com.acalv3.commons.formatDocument
import br.com.acalv3.domain.model.Link

class LinkReport(
    val customerName: String,
    val customerDocument: String,
    val addressName: String,
    val number: String,
    val groupName: String,
    val categoryName: String,
)

fun Link.toLinkReport() = LinkReport(
    customerName = customer.name,
    customerDocument = customer.document.formatDocument(),
    addressName = place.address.name,
    number = """${place.number} ${place.letter}""",
    groupName = group.name,
    categoryName = group.category.value,
)

fun List<Link>.toLinkReport() = map { it.toLinkReport() }