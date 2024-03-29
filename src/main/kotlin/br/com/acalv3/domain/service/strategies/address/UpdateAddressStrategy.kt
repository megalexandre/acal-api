package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class UpdateAddressStrategy(
    val repository: AddressRepository,
):  AddressStrategy<Address>  {

    override fun action() = UPDATE

    override fun can(model: Address) {
        repository.findByName(model.name)?.let {
            if(it.id != model.id){
                throw RuntimeException(String.format(ERROR_DUPLICATED, model.name))
            }
        }
    }

    companion object{
        private const val ERROR_DUPLICATED = "O Endereço %s já está cadastrado"
    }

}