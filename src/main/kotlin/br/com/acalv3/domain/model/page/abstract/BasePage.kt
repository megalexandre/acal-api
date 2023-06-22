package br.com.acalv3.domain.model.page.abstract

import br.com.acalv3.domain.enumeration.Direction
import br.com.acalv3.domain.enumeration.Direction.ASC

abstract class BasePage (
    open val page: Int,
    open val pageSize: Int = 10,
    open val sortedField: String = "id",
    open val direction: Direction = ASC,
)
