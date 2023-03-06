package br.com.acalv3.domain.service.strategies.place

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.domain.service.LinkService
import org.springframework.stereotype.Service

@Service
class DeletePlaceStrategy(
    val repository: PlaceRepository,
    val linkService: LinkService
): PlaceStrategy {

    override fun action() = DELETE

    override fun can(place: Place) {
        linkService.findByPlaceId(place.id)?.let {
            throw RuntimeException("Esse endereço está vinculado a uma ligação e não pode ser apagado")
        }
    }

}