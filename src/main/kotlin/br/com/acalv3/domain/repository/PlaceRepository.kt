package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.page.PlacePage

interface PlaceRepository: AbstractRepository<Place, PlacePage> {
    fun update(place: Place): Place
    fun findPlace(place: Place): Place?
    fun findByAddress(address: Address): Place?
}