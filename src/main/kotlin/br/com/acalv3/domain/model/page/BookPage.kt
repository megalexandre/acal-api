package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.enumeration.Reason
import br.com.acalv3.domain.enumeration.Type
import br.com.acalv3.domain.model.page.abstract.BasePage
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class BookPage (
    val id: UUID? = null,
    val value: BigDecimal? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val type: Type? = null,
    val reason: Reason? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",

): BasePage(
    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction,
)

