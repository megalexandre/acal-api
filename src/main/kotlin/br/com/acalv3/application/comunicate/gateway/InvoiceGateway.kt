package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.invoice.InvoiceRequest
import br.com.acalv3.application.comunicate.model.request.invoice.toInvoice
import br.com.acalv3.application.comunicate.model.response.address.AddressGetResponse
import br.com.acalv3.application.comunicate.model.response.address.toGetAddressResponse
import br.com.acalv3.application.comunicate.model.response.invoice.InvoiceResponse
import br.com.acalv3.application.comunicate.model.response.invoice.toInvoiceResponse
import br.com.acalv3.domain.service.InvoiceService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("invoice",
    produces=[ "application/json" ],
)
class InvoiceGateway(
    val service: InvoiceService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun save(@Valid @RequestBody request: InvoiceRequest): InvoiceResponse = run {
        logger.info("saving invoice: $request")
        service.save(request.toInvoice()).toInvoiceResponse()
    }

    @GetMapping("/list")
    fun list(): List<InvoiceResponse> = run {
        logger.info("listing all address")
        service.getAll().toInvoiceResponse()
    }

}