package br.com.acalv3.application.comunication.model.request.quality.request

import br.com.acalv3.application.comunication.model.request.pagination.BasePageRequest
import br.com.acalv3.domain.model.page.QualityPage

class QualityPageRequest: BasePageRequest()

fun QualityPageRequest.toPage() = QualityPage(
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)