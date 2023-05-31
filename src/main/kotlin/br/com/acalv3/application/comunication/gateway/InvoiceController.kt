package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.INVOICE
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.LIST
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunication.model.request.invoice.InvoicePageRequest
import br.com.acalv3.application.comunication.model.request.invoice.InvoiceSaveRequest
import br.com.acalv3.application.comunication.model.request.invoice.toInvoice
import br.com.acalv3.application.comunication.model.request.invoice.toPageRequest
import br.com.acalv3.application.comunication.model.response.invoice.InvoicePageResponse
import br.com.acalv3.application.comunication.model.response.invoice.InvoiceResponse
import br.com.acalv3.application.comunication.model.response.invoice.toInvoiceResponse
import br.com.acalv3.domain.service.InvoiceService
import java.util.UUID
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.APPLICATION_PDF_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(INVOICE,
    produces=[APPLICATION_JSON_VALUE],
)
private class InvoiceGateway(
    val service: InvoiceService,
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping(PAGINATE)
    fun paginate(@Valid @RequestBody request: InvoicePageRequest): Page<InvoicePageResponse> =
        service.paginate(request.toPageRequest()).toInvoiceResponse().also{
            logger.info("paginate invoice: $request")
        }

    @PostMapping
    fun save(@Valid @RequestBody request: List<InvoiceSaveRequest>): List<InvoiceResponse> =
        service.saveAll(request.toInvoice()).toInvoiceResponse().also{
            logger.info("save invoice: $request")
        }

    @GetMapping(LIST)
    fun list(): List<InvoiceResponse> =
        service.getAll().toInvoiceResponse().also{
            logger.info("listing all invoice")
        }

    @PostMapping(path = ["/report/{id}"], produces = [APPLICATION_PDF_VALUE])
    fun reportById(@PathVariable id: String): ByteArray? =
        service.report(UUID.fromString(id)).also {
            logger.info("getting report by id: $id")
        }
}