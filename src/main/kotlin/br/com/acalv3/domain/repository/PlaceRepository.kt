package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import org.springframework.data.domain.Page

interface PlaceRepository {
    fun getById(id: String): Place
    fun save(place: Place): Place
    fun update(place: Place): Place
    fun delete(id: String)
    fun findPlace(place: Place): Place?
    fun paginate(request: PlacePage): Page<Place>
    fun findByAddress(address: Address): Place?
}