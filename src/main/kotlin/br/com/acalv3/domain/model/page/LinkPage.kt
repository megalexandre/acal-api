package br.com.acalv3.domain.model.page

data class LinkPage (
    val name: String?,

    val customer: CustomerPage? = null,
    val place: PlacePage? = null,
    val group: GroupPage? = null,
    val active: Boolean? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",
) : DefaultPage()
