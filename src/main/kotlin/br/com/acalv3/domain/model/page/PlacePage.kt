package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC
import br.com.acalv3.domain.filter.AddressFilter
import br.com.acalv3.domain.model.page.abstract.BasePage

data class PlacePage (
    val letter: String? = null,
    val number: Long? = null,
    val address: AddressFilter? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: Direction = ASC,

    ) : BasePage(
    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction,
)
