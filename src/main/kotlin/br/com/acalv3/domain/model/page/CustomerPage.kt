package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.model.page.abstract.BasePage
import java.time.LocalDate

data class CustomerPage (
    val name: String?,
    val document: String?,
    val birthDay: LocalDate? = null,
    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",
) : BasePage(
    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction,
)
