package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.domain.service.strategies.address.AddressStrategy

abstract class AddressService: AbstractService<Address, AddressPage>() {
    abstract override fun strategies(): List<AddressStrategy<Address>>
    abstract override fun repository(): AddressRepository
}
