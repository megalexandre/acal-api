package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.invoice.InvoicePageRequest
import br.com.acalv3.application.comunicate.model.request.invoice.InvoiceRequest
import br.com.acalv3.application.comunicate.model.request.invoice.InvoiceTest
import br.com.acalv3.application.comunicate.model.request.invoice.toInvoice
import br.com.acalv3.application.comunicate.model.request.invoice.toPageRequest
import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.application.comunicate.model.request.link.toPageRequest
import br.com.acalv3.application.comunicate.model.response.invoice.InvoicePageResponse
import br.com.acalv3.application.comunicate.model.response.invoice.InvoiceResponse
import br.com.acalv3.application.comunicate.model.response.invoice.toInvoiceResponse
import br.com.acalv3.application.comunicate.model.response.link.LinkPageResponse
import br.com.acalv3.application.comunicate.model.response.link.toLinkResponse
import br.com.acalv3.domain.service.InvoiceService
import br.com.acalv3.domain.service.LinkService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.APPLICATION_PDF_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("invoice",
    produces=[APPLICATION_JSON_VALUE],
)
class InvoiceGateway(
    val service: InvoiceService,
    val linkService: LinkService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: InvoicePageRequest): Page<InvoicePageResponse> =
        service.paginate(request.toPageRequest()).toInvoiceResponse()

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun save(@Valid @RequestBody request: List<InvoiceRequest>): List<InvoiceResponse> {
        logger.info("saving invoice: $request")
        return service.saveAll(request.map {
            it.toInvoice(linkService.getById(it.id!!))
        }).toInvoiceResponse()
    }

    @GetMapping("/list")
    fun list(): List<InvoiceResponse> = run {
        logger.info("listing all address")
        service.getAll().toInvoiceResponse()
    }

    @PostMapping(path = ["/report"], produces = [APPLICATION_PDF_VALUE])
    fun report(): ByteArray? = service.report()

}