package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC
import br.com.acalv3.domain.model.page.abstract.BasePage
import java.time.LocalDate

data class InvoicePage (
    val id: String? = null,
    val reference: String? = null,
    val value: Number?= null,
    val dueDate: LocalDate? = null,
    val customerName: String? = null,
    val addressId: String? = null,
    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: Direction = ASC,
) : BasePage (
    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction,
)
