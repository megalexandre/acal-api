package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class SaveAddressStrategy(
    val repository: AddressRepository,
): AddressStrategy {

    override fun action() = SAVE

    override fun can(address: Address) {
        repository.findByName(address.name)?.let {
            throw RuntimeException("O Endereço ${address.name} já está cadastrado")
        }
    }
}