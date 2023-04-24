package br.com.acalv3.application.comunication.model.request.link

import br.com.acalv3.application.comunication.model.request.customer.CustomerPageRequest
import br.com.acalv3.application.comunication.model.request.customer.toCustomerPage
import br.com.acalv3.application.comunication.model.request.group.GroupPageRequest
import br.com.acalv3.application.comunication.model.request.group.toGroupPage
import br.com.acalv3.application.comunication.model.request.pagination.DefaultPageRequest
import br.com.acalv3.application.comunication.model.request.place.PlacePageRequest
import br.com.acalv3.application.comunication.model.request.place.toPlacePage
import br.com.acalv3.domain.model.page.LinkPage

data class LinkPageRequest(

    val name: String? = null,
    val customer: CustomerPageRequest? = null,
    val place: PlacePageRequest? = null,
    val group: GroupPageRequest? = null,
    val active: Boolean? = null,

    override val sortedField: String = "id",
    override val page: Int = 0,
    override val pageSize: Int = 10,
    override val direction: String = "ASC",

    ) : DefaultPageRequest()

fun LinkPageRequest.toPageRequest() = LinkPage(
    name = name,
    customer = customer?.toCustomerPage(),
    place = place?.toPlacePage(),
    group = group?.toGroupPage(),
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
    active = active
)

