package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.Fixture
import br.com.acalv3.application.comunicate.Fixture.Companion.APPLICATION_PRODUCES
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
import java.util.UUID
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.APPLICATION_PDF_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("invoice",
    produces=[APPLICATION_PRODUCES],
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
    fun list(): List<InvoiceResponse> =
        service.getAll().toInvoiceResponse().also{
            logger.info("listing all address")
        }

    @PostMapping(path = ["/report/{id}"], produces = [APPLICATION_PDF_VALUE])
    fun reportById(@PathVariable id: String): ByteArray? =
        service.report(UUID.fromString(id)).also {
            logger.info("getting report by id: $id")
        }

    @PostMapping(path = ["/report"], produces = [APPLICATION_PDF_VALUE])
    fun reportLot(@Valid @RequestBody request: InvoicePageRequest): ByteArray? = service.report()

}