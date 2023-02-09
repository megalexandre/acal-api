package br.com.acalv3.domain.service.impl

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.domain.service.AddressService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class AddressServiceImplServiceImpl(
	val repository: AddressRepository,
): AddressService {
	override fun getById(id: String): Address =
		repository.getById(id)

	override fun save(address: Address): Address =
		repository.save(address)

	override fun update(address: Address): Address =
		repository.save(address)

	override fun findByName(name: String): Address =
        repository.findByName(name)

	override fun paginate(addressPageRequest: AddressPageRequest): Page<Address> =
		repository.paginate(addressPageRequest)

}
