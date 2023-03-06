package br.com.acalv3.domain.service.strategies.place

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class SavePlaceStrategy(
    val repository: PlaceRepository,
): PlaceStrategy {

    override fun action() = SAVE

    override fun can(place: Place) {
        repository.findPlace(place)?.let {
            throw RuntimeException("O Endereço já está cadastrado")
        }
    }
}