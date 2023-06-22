package br.com.acalv3.application.comunication.model.request.invoice

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.model.page.InvoicePage
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class InvoicePageRequest(

    val id: String? = null,
    val reference: String? = null,
    val value: Number?= null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    val dueDate: LocalDate? = null,
    val customerName: String? = null,

    override val sortedField: String = "id",
    override val direction: Direction = Direction.ASC,
    override val page: Int = 0,
    override val pageSize: Int = 10,
    ) : DefaultPageRequest()

fun InvoicePageRequest.toPageRequest() = InvoicePage(
    id = id,
    reference = reference,
    value = value,
    dueDate = dueDate,
    customerName = customerName?.uppercase(),
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction
)

