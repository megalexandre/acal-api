package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.application.comunicate.model.request.customer.CustomerSaveRequest
import br.com.acalv3.application.comunicate.model.request.customer.CustomerUpdateRequest
import br.com.acalv3.application.comunicate.model.request.customer.toCustomer
import br.com.acalv3.application.comunicate.model.response.customer.*
import br.com.acalv3.domain.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("customer", produces=[ "application/json" ])
class CustomerGateway(
    val customerService: CustomerService) {

    @PostMapping
    fun save(@Valid @RequestBody request: CustomerSaveRequest): SaveUpdateCustomerResponse =
        customerService.save(request.toCustomer()).toCustomerResponse()

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: CustomerUpdateRequest) =
        customerService.update(request.toCustomer()).toCustomerResponse()

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: CustomerPageRequest): Page<CustomerPageResponse> =
        customerService.paginate(request).toCustomerPageResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): GetCustomerResponse =
        customerService.getById(id).toGetCustomerResponse()

}