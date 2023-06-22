package br.com.acalv3.application.comunication.model.request.book

import br.com.acalv3.application.comunication.Fixture.Companion.DATE_FORMAT
import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.model.page.BookPage
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class BookPageRequest(

    val id: UUID? = null,
    val value: BigDecimal? = null,
    val createdBy: String? = null,
    @JsonFormat(shape = STRING, pattern = DATE_FORMAT)
    val createdAt: LocalDate? = null,
    val type: Type? = null,
    val reason: Reason? = null,

    val createdAtFinish: LocalDate? = null,
    val createdAtStarted: LocalDate? = null,

    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: Direction = Direction.ASC,
    override val sortedField: String = "id",

    ): DefaultPageRequest()

fun BookPageRequest.toPage() = BookPage(
    id = id,
    value = value,
    createdBy = createdBy,
    createdAt = createdAt?.atStartOfDay(),
    type = type,
    reason = reason,
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
    createdAtFinish = createdAtFinish,
    createdAtStarted = createdAtStarted,
)