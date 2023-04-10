package br.com.acalv3.application.comunicate.model.request.hydrometer

import br.com.acalv3.application.comunicate.model.request.pagination.BasePageRequest
import br.com.acalv3.domain.model.page.HydrometerPage

class HydrometerPageRequest: BasePageRequest()

fun HydrometerPageRequest.toPage() = HydrometerPage(
    sortedField = sortedField,
    page = page,
    pageSize = pageSize,
    direction = direction,
)