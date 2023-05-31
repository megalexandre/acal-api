package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.BY_ID
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.CUSTOMER_ROUTE
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.UPDATE
import br.com.acalv3.application.comunication.model.request.customer.CustomerPageRequest
import br.com.acalv3.application.comunication.model.request.customer.CustomerSaveRequest
import br.com.acalv3.application.comunication.model.request.customer.CustomerUpdateRequest
import br.com.acalv3.application.comunication.model.request.customer.toCustomer
import br.com.acalv3.application.comunication.model.request.customer.toCustomerPage
import br.com.acalv3.application.comunication.model.response.customer.CustomerGetResponse
import br.com.acalv3.application.comunication.model.response.customer.CustomerPageResponse
import br.com.acalv3.application.comunication.model.response.customer.SaveUpdateCustomerResponse
import br.com.acalv3.application.comunication.model.response.customer.toCustomerPageResponse
import br.com.acalv3.application.comunication.model.response.customer.toCustomerResponse
import br.com.acalv3.application.comunication.model.response.customer.toGetCustomerResponse
import br.com.acalv3.domain.service.CustomerService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
class CustomerController(
    val customerService: CustomerService
    ) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun save(@Valid @RequestBody request: CustomerSaveRequest): SaveUpdateCustomerResponse =
        customerService.save(request.toCustomer()).toCustomerResponse().also {
            logger.info("save customer: $request")
        }

    @PutMapping(UPDATE)
    fun update(@Valid @RequestBody request: CustomerUpdateRequest) =
        customerService.update(request.toCustomer()).toCustomerResponse().also {
            logger.info("update customer: $request")
        }

    @PostMapping(PAGINATE)
    fun paginate(@Valid @RequestBody request: CustomerPageRequest): Page<CustomerPageResponse> =
        customerService.paginate(request.toCustomerPage()).toCustomerPageResponse().also {
            logger.info("paginate customer: $request")
        }

    @GetMapping(BY_ID)
    fun find(@PathVariable id: String): CustomerGetResponse =
        customerService.getById(id).toGetCustomerResponse().also {
            logger.info("getById customer: $id")
        }

    @DeleteMapping(BY_ID)
    fun delete(@PathVariable id: String) = customerService.delete(id).also {
        logger.info("delete customer: $id")
    }

}