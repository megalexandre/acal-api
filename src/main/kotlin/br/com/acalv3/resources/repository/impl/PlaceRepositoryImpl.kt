package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.filter.toAddress
import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.resources.model.business.toPlace
import br.com.acalv3.resources.model.business.toPlaceEntity
import br.com.acalv3.resources.model.business.toPlacePage
import br.com.acalv3.resources.repository.interfaces.PlaceRepositoryJpa
import br.com.acalv3.resources.repository.specification.PlaceSpecification
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PlaceRepositoryImpl(
    private val repository: PlaceRepositoryJpa,
) : PlaceRepository {

    override fun getById(id: String): Place =
        repository.findByIdOrNull(UUID.fromString(id))?.toPlace() ?: throw NotFoundException()

    override fun findAll(): List<Place> = repository.findAll().toPlace()
    override fun save(type: Place): Place = repository.save(type.toPlaceEntity()).toPlace()
    override fun saveAll(type: List<Place>) {
        TODO("Not yet implemented")
    }

    override fun update(place: Place): Place =  repository.save(place.toPlaceEntity()).toPlace()
    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))
    override fun count(): Long = repository.count()

    override fun findPlace(place: Place): Place?  =
        repository.findAll(
            PlaceSpecification(PlacePage(
                letter = place.letter,
                number = place.number,
                addressId = place.addressId,
            )).getSpecification()
        ).firstOrNull()?.toPlace()

    override fun paginate(page: PlacePage): Page<Place> =
        repository.findAll(
            PlaceSpecification(page).getSpecification(),
            super.pageable(page)
        ).toPlacePage()

    override fun findAll(page: PlacePage): List<Place> =
        repository.findAll(
            PlaceSpecification(page).getSpecification(),
        ).toPlace()


    override fun findByAddress(address: Address): Place? =
        repository.findAll(
            PlaceSpecification(PlacePage(
                address = address.toAddress()
            )).getSpecification()
        ).firstOrNull()?.toPlace()
}
