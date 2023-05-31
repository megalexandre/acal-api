package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.service.PlaceService
import org.springframework.stereotype.Service

@Service
class DeleteAddressStrategy(
    val placeService: PlaceService
    ) : AddressStrategy<Address> {

    override fun action() = DELETE

    override fun can(model: Address) {
        placeService.findByAddress(model)?.let {
            throw RuntimeException("Esse Logradouro está vinculo a endereço(s) e não pode ser deletado")
        }
    }

}