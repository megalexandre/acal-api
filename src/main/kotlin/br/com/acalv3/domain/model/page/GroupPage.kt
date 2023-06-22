package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.model.page.abstract.BasePage
import java.math.BigDecimal

data class GroupPage (
    val name: String?,
    val value: BigDecimal? = null,
    val category: Category?,
    val categoryValue: BigDecimal? = null,
    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: Direction = Direction.ASC,
) : BasePage(
    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction,

)

