package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage
import br.com.acalv3.domain.repository.AddressRepository
import br.com.acalv3.domain.service.AddressService
import br.com.acalv3.domain.service.strategies.address.AddressStrategy
import br.com.acalv3.domain.service.strategies.place.PlaceStrategy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
	val repository: AddressRepository,
	val strategies: List<AddressStrategy>
): AddressService {

	override fun getById(id: String): Address = repository.getById(id)

	override fun save(address: Address): Address =
		strategies.first{ it.action() === SAVE }.can(address).let {
			repository.save(address)
		}

	override fun update(address: Address): Address =
		strategies.first{ it.action() === UPDATE }.can(address).let {
			repository.update(address)
		}

	override fun delete(id: String) =
		strategies.first{ it.action() === DELETE }.can(repository.getById(id)).let {
			repository.delete(id)
		}

	override fun paginate(pageRequest: AddressPage): Page<Address> = repository.paginate(pageRequest)

	override fun getAll(): List<Address> = repository.getAll()
}
