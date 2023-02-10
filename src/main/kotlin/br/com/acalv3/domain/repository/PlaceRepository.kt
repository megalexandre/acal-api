package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import org.springframework.data.domain.Page

interface PlaceRepository {
    fun getById(id: String): Place
    fun save(place: Place): Place
    fun update(customer: Place): Place
    fun paginate(pageRequest: PlacePage): Page<Place>
}