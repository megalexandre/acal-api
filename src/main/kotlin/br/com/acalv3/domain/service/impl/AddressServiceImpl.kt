package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.domain.service.AddressService
import br.com.acalv3.domain.service.strategies.address.AddressStrategy
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
	val repository: AddressRepository,
	val strategies: List<AddressStrategy<Address>>
): AddressService() {
	override fun strategies(): List<AddressStrategy<Address>> = strategies
	override fun repository(): AddressRepository = repository
}