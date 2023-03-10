package br.com.acalv3.application.comunicate.model.quality.request

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.application.comunicate.model.request.pagination.BasePageRequest
import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.domain.model.page.QualityPage

class QualityPageRequest: BasePageRequest()

fun QualityPageRequest.toPage() = QualityPage(
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)