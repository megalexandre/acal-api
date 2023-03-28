package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.domain.service.ReportService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("report")
class ReportGateway(
    val service: ReportService
) {

    @GetMapping(path = ["/link"], produces = [MediaType.APPLICATION_PDF_VALUE])
    fun link(): ByteArray? = service.link()

}