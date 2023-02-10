package br.com.acalv3.resources.repository.impl

import br.com.acalv3.application.comunicate.model.request.place.PlacePageRequest
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.resources.model.business.toPlace
import br.com.acalv3.resources.model.business.toPlaceEntity
import br.com.acalv3.resources.model.business.toPlacePage
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.PlaceRepositoryJpa
import br.com.acalv3.resources.repository.specification.PlaceSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PlaceRepositoryImpl(
    private val repository: PlaceRepositoryJpa,
) : PlaceRepository, DefaultRepository {

    override fun getById(id: String): Place =
        repository.findByIdOrNull(UUID.fromString(id))?.toPlace() ?: throw NotFoundException()

    override fun save(place: Place): Place =
        repository.save(place.toPlaceEntity()).toPlace()

    override fun update(place: Place): Place =
        repository.save(place.toPlaceEntity()).toPlace()

    override fun paginate(request: PlacePage): Page<Place> =
        repository.findAll(
            PlaceSpecification(request).getSpecification(),
            super.pageable(request)
        ).toPlacePage()

}
