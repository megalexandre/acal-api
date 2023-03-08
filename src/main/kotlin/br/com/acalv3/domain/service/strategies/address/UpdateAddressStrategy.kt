package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class UpdateAddressStrategy(
    val repository: AddressRepository,
): AddressStrategy {

    override fun action() = UPDATE

    override fun can(address: Address) {
        repository.findByName(address.name)?.let {
            if(it.id != address.id){
                throw RuntimeException("O Endereço ${address.name} já está cadastrado")
            }
        }
    }

}