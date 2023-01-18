package br.com.acalv3.application.toEnter.gateway.v4

import br.com.acalv3.application.toEnter.comunicate.request.BillPaymentRequest
import br.com.acalv3.domain.service.v3.AddressService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bill")
class BillGateway{

    @PostMapping("payment")
    fun billPayment(@RequestBody paymentRequest: BillPaymentRequest) = "ok"

}