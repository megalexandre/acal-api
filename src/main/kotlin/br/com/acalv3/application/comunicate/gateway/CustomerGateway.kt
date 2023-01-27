package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.CustomerPageRequest
import br.com.acalv3.application.comunicate.model.request.SaveCustomerRequest
import br.com.acalv3.application.comunicate.model.request.toCustomer
import br.com.acalv3.application.comunicate.model.response.*
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("customer", produces=[ "application/json" ])
class CustomerGateway(
    val customerService: CustomerService) {

    @PostMapping
    fun saveCustomer(@Valid @RequestBody saveCustomerRequest: SaveCustomerRequest): SaveCustomerResponse =
        customerService.save(saveCustomerRequest.toCustomer()).toCustomerResponse()

    @PostMapping("/paginate")
    fun paginateCustomer(@Valid @RequestBody customerPageRequest: CustomerPageRequest): Page<CustomerPageResponse> =
        customerService.paginate(customerPageRequest).toCustomerPageResponse()

    @GetMapping("/{id}")
    fun paginateCustomer(@PathVariable id: UUID): GetCustomerResponse =
        customerService.getById(id).toGetCustomerResponse()

}