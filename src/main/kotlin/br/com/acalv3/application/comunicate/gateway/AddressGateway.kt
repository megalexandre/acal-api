package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.address.*
import br.com.acalv3.application.comunicate.model.response.address.*
import br.com.acalv3.application.comunicate.model.response.customer.*
import br.com.acalv3.domain.service.AddressService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("address", produces=[ "application/json" ])
class AddressGateway(
    val service: AddressService
) {

    @PostMapping
    fun save(@Valid @RequestBody request: AddressSaveRequest): SaveUpdateAddressResponse =
        service.save(request.toAddress()).toAddressResponse()

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: AddressUpdateRequest) =
        service.update(request.toAddress()).toAddressResponse()

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: AddressPageRequest): Page<AddressPageResponse> =
        service.paginate(request).toAddressPageResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): AddressGetResponse =
        service.getById(id).toGetAddressResponse()

}