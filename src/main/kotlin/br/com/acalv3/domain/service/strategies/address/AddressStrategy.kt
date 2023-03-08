package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Address

interface AddressStrategy  {
    fun action(): Action
    fun can(address: Address)
}