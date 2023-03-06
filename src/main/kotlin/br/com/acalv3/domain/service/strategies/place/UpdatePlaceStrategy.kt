package br.com.acalv3.domain.service.strategies.place

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class UpdatePlaceStrategy(
    val repository: PlaceRepository,
): PlaceStrategy {

    override fun action() = UPDATE

    override fun can(place: Place) {
        repository.findPlace(place)?.let {
            if(it.id != place.id){
                throw RuntimeException("O Endereço já está cadastrado")
            }
        }
    }

}