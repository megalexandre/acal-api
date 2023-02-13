package br.com.acalv3.domain.model.page

import br.com.acalv3.domain.model.Address

data class PlacePage (
    val letter: String?,
    val number: Int?,
    val address: Address?,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",
) : DefaultPage()
