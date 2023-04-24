package br.com.acalv3.application.comunication.model.request.invoice

import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.InvoicePage

data class InvoicePageRequest(

    override val sortedField: String = "id",
    override val direction: String = "ASC",
    override val page: Int = 0,
    override val pageSize: Int = 10,

    ) : DefaultPageRequest()

fun InvoicePageRequest.toPageRequest() = InvoicePage(
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction
)

