package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.domain.service.PlaceService
import br.com.acalv3.domain.service.strategies.place.PlaceStrategy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PlaceServiceImpl(
	val repository: PlaceRepository,
	val strategies: List<PlaceStrategy>
): PlaceService {

	override fun getById(id: String): Place =
		repository.getById(id)

	override fun save(place: Place): Place =
		strategies.first{ it.action() === SAVE }.can(place).let {
			repository.save(place)
		}

	override fun update(place: Place): Place =
		strategies.first{ it.action() === UPDATE }.can(place).let {
			repository.update(place)
		}

	override fun delete(id: String) =
		strategies.first{ it.action() === DELETE }.can(repository.getById(id)).let {
			repository.delete(id)
		}

	override fun findByAddress(address: Address): Place? = repository.findByAddress(address)

	override fun paginate(placePageRequest: PlacePage): Page<Place> =
		repository.paginate(placePageRequest)



}
