package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.ADDRESS_ROUTE
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.BY_ID
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.LIST
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.UPDATE
import br.com.acalv3.application.comunication.model.request.address.AddressPaginateRequest
import br.com.acalv3.application.comunication.model.request.address.AddressSaveRequest
import br.com.acalv3.application.comunication.model.request.address.AddressUpdateRequest
import br.com.acalv3.application.comunication.model.request.address.toAddress
import br.com.acalv3.application.comunication.model.request.address.toAddressPage
import br.com.acalv3.application.comunication.model.response.address.AddressGetResponse
import br.com.acalv3.application.comunication.model.response.address.AddressPageResponse
import br.com.acalv3.application.comunication.model.response.address.SaveUpdateAddressResponse
import br.com.acalv3.application.comunication.model.response.address.toAddressPageResponse
import br.com.acalv3.application.comunication.model.response.address.toAddressResponse
import br.com.acalv3.application.comunication.model.response.address.toGetAddressResponse
import br.com.acalv3.domain.service.AddressService
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
@RequestMapping(ADDRESS_ROUTE, produces=[APPLICATION_JSON_VALUE])
class AddressController(
    val service: AddressService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun save(@Valid @RequestBody request: AddressSaveRequest): SaveUpdateAddressResponse =
        service.save(request.toAddress()).toAddressResponse().also {
            logger.info("save address: $request")
        }

    @PutMapping(UPDATE)
    fun update(@Valid @RequestBody request: AddressUpdateRequest) =
        service.update(request.toAddress()).toAddressResponse().also {
            logger.info("save update: $request")
        }

    @PostMapping(PAGINATE)
    fun paginate(@Valid @RequestBody request: AddressPaginateRequest): Page<AddressPageResponse> =
        service.paginate(request.toAddressPage()).toAddressPageResponse().also {
            logger.info("save paginate: $request")
        }

    @GetMapping(BY_ID)
    fun find(@PathVariable id: String): AddressGetResponse =
        service.getById(id).toGetAddressResponse().also {
            logger.info("find: $id")
        }

    @GetMapping(LIST)
    fun list(): List<AddressGetResponse> =
        service.getAll().toGetAddressResponse().also {
            logger.info("list all")
        }

    @DeleteMapping(BY_ID)
    fun delete(@PathVariable id: String) =
        service.delete(id).also {
            logger.info("delete by id: $id")
        }

}