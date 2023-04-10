package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.BY_ID
import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.CUSTOMER_ROUTE
import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.UPDATE
import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.application.comunicate.model.request.customer.CustomerSaveRequest
import br.com.acalv3.application.comunicate.model.request.customer.CustomerUpdateRequest
import br.com.acalv3.application.comunicate.model.request.customer.toCustomer
import br.com.acalv3.application.comunicate.model.request.customer.toCustomerPage
import br.com.acalv3.application.comunicate.model.response.customer.CustomerGetResponse
import br.com.acalv3.application.comunicate.model.response.customer.CustomerPageResponse
import br.com.acalv3.application.comunicate.model.response.customer.SaveUpdateCustomerResponse
import br.com.acalv3.application.comunicate.model.response.customer.toCustomerPageResponse
import br.com.acalv3.application.comunicate.model.response.customer.toCustomerResponse
import br.com.acalv3.application.comunicate.model.response.customer.toGetCustomerResponse
import br.com.acalv3.domain.service.CustomerService
import javax.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(CUSTOMER_ROUTE,
    produces=[APPLICATION_JSON_VALUE]
)
class CustomerGateway(
    val customerService: CustomerService
    ) {

    @PostMapping
    fun save(@Valid @RequestBody request: CustomerSaveRequest): SaveUpdateCustomerResponse =
        customerService.save(request.toCustomer()).toCustomerResponse()

    @PutMapping(UPDATE)
    fun update(@Valid @RequestBody request: CustomerUpdateRequest) =
        customerService.update(request.toCustomer()).toCustomerResponse()

    @PostMapping(PAGINATE)
    fun paginate(@Valid @RequestBody request: CustomerPageRequest): Page<CustomerPageResponse> =
        customerService.paginate(request.toCustomerPage()).toCustomerPageResponse()

    @GetMapping(BY_ID)
    fun find(@PathVariable id: String): CustomerGetResponse =
        customerService.getById(id).toGetCustomerResponse()

    @DeleteMapping(BY_ID)
    fun delete(@PathVariable id: String) = customerService.delete(id)

}