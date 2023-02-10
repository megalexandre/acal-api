package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage
import org.springframework.data.domain.Page

interface PlaceService {
    fun getById(id: String): Place
    fun save(place: Place): Place
    fun update(place: Place): Place
    fun paginate(placePageRequest: PlacePage): Page<Place>
}