package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.CustomerRequest
import br.com.acalv3.application.comunicate.model.request.toCustomer
import br.com.acalv3.application.comunicate.model.response.CustomerResponse
import br.com.acalv3.application.comunicate.model.response.toCustomerResponse
import br.com.acalv3.domain.service.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("customer", produces=[ "application/json" ])
class CustomerGateway(
    val customerService: CustomerService) {

    @PostMapping
    fun billPayment(@Valid @RequestBody customerRequest: CustomerRequest): CustomerResponse =
        customerService.save(customerRequest.toCustomer()).toCustomerResponse()

}