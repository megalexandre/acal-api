package br.com.acalv3.domain.service.strategies.address

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface AddressStrategy<Q: Address> : AbstractStrategy<Address> {
    override fun action(): Action
    override fun can(model: Address)
}