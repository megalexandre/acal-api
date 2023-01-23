package br.com.acalv3.application.toEnter.gateway

import br.com.acalv3.application.toEnter.comunicate.request.BillPaymentRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bill", produces=[ "application/json" ])
class BillGateway{

    @PostMapping("payment")
    fun billPayment(@RequestBody paymentRequest: BillPaymentRequest) = "{\"status\":\"ok\"}"

}