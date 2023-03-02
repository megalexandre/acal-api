package br.com.acalv3.application.comunicate.model.request.customer

import br.com.acalv3.application.comunicate.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.application.comunicate.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.model.page.CustomerPage
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.time.LocalDate

data class CustomerPageRequest(

    val name: String? = null,
    val document: String? = null,
    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    val birthDay: LocalDate? = null,
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",
    override val sortedField: String = "id",
    val active: Boolean? = false,

): DefaultPageRequest()

fun CustomerPageRequest.toCustomerPage() = CustomerPage(
    name = name,
    document = document,
    birthDay = birthDay,
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)