package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.application.comunicate.model.request.link.toPageRequest
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.PlaceService
import br.com.acalv3.domain.service.ReportService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("report")
class ReportGateway(
    val service: ReportService,
    val customerService: CustomerService,
    val placeService: PlaceService,
    val groupService: GroupService,
) {

    @PostMapping(path = ["/link"], produces = [MediaType.APPLICATION_PDF_VALUE])
    fun link(@RequestBody request: LinkPageRequest): ByteArray? = service.link(
        request.toPageRequest()
    )

}