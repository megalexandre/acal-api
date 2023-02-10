package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.bill.BillPaymentRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bill",
    produces=[ "application/json" ],
    consumes = ["application/json"]
)
class BillGateway{
    @PostMapping("payment")
    fun billPayment(@RequestBody paymentRequest: BillPaymentRequest) = "{\"status\":\"ok\"}"

}