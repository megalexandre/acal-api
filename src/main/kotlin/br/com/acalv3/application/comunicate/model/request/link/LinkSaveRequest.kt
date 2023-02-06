package br.com.acalv3.application.comunicate.model.request.link

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Link
import java.util.*

class LinkSaveRequest: LinkRequest()

fun LinkSaveRequest.toLink(customer: Customer) = Link(
    id = UUID.randomUUID().toString(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    customer = customer,
)