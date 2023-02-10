package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.domain.service.AddressService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
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

	override fun paginate(pageRequest: AddressPage): Page<Address> =
		repository.paginate(pageRequest)

	override fun getAll(): List<Address> =
		repository.getAll()
}
