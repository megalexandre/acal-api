package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.application.comunicate.model.request.address.AddressSaveRequest
import br.com.acalv3.application.comunicate.model.request.address.AddressUpdateRequest
import br.com.acalv3.application.comunicate.model.request.address.toAddress
import br.com.acalv3.application.comunicate.model.request.address.toAddressPage
import br.com.acalv3.application.comunicate.model.response.address.AddressGetResponse
import br.com.acalv3.application.comunicate.model.response.address.AddressPageResponse
import br.com.acalv3.application.comunicate.model.response.address.SaveUpdateAddressResponse
import br.com.acalv3.application.comunicate.model.response.address.toAddressPageResponse
import br.com.acalv3.application.comunicate.model.response.address.toAddressResponse
import br.com.acalv3.application.comunicate.model.response.address.toGetAddressResponse
import br.com.acalv3.application.configuration.interceptor.CustomAccessDeniedHandler
import br.com.acalv3.domain.service.AddressService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("address",
    produces=[ "application/json" ],
    consumes = ["application/json"]
)
class AddressGateway(
    val service: AddressService
) {
    private var logger: Logger = LoggerFactory.getLogger(CustomAccessDeniedHandler::class.java)

    @PostMapping
    fun save(@Valid @RequestBody request: AddressSaveRequest): SaveUpdateAddressResponse =
        service.save(request.toAddress()).toAddressResponse()

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: AddressUpdateRequest) =
        service.update(request.toAddress()).toAddressResponse()

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: AddressPageRequest): Page<AddressPageResponse> =
        service.paginate(request.toAddressPage()).toAddressPageResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): AddressGetResponse =
        service.getById(id).toGetAddressResponse()

}