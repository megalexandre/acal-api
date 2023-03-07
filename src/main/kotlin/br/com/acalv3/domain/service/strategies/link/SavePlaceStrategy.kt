package br.com.acalv3.domain.service.strategies.link

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.repository.LinkRepository
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.domain.service.LinkService
import org.springframework.stereotype.Service

@Service
class SaveLinkStrategy(
    val repository: LinkRepository,
): LinkStrategy {

    override fun action() = SAVE

    override fun can(link: Link) {
        repository.findByPlaceId(link.place.id)?.let {
            throw RuntimeException(
                """
                    A ligação não pode ser realizada, porquê o enderço já está associado a outro cliente
                    ${link.customer.name}
                """)
        }
    }

}