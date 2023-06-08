package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.model.page.abstract.BasePage
import java.math.BigDecimal

data class GroupPage (
    val name: String?,
    val value: BigDecimal? = null,
    val category: Category?,
    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",
    val categoryValue: BigDecimal? = null,
) : BasePage(
    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction,

)

