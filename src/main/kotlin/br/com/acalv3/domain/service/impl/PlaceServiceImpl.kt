package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.domain.service.PlaceService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class PlaceServiceImpl(
	val repository: PlaceRepository,
): PlaceService {

	override fun getById(id: String): Place =
		repository.getById(id)

	override fun save(place: Place): Place =
		repository.save(place)

	override fun update(place: Place): Place =
		repository.save(place)

	override fun paginate(placePageRequest: PlacePage): Page<Place> =
		repository.paginate(placePageRequest)

}
