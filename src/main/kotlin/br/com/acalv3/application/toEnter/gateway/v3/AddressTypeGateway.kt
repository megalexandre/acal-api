package br.com.acalv3.application.toEnter.gateway.v3

import br.com.acalv3.application.toEnter.gateway.AppGateway
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.service.v3.AddressTypeService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("address-type")
class AddressTypeGateway(
    val service: AddressTypeService
): AppGateway<AddressTypeModel>(service)