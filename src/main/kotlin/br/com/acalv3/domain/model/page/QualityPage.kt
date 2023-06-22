package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC
import br.com.acalv3.domain.model.page.abstract.BasePage

class QualityPage(

    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val sortedField: String = "id",
    override val direction: Direction = ASC,
    val reference: List<String>? = null,

    ): BasePage(

    page = page,
    pageSize = pageSize,
    sortedField = sortedField,
    direction = direction
)

