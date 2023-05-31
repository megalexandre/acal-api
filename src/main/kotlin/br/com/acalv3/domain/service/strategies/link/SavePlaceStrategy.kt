package br.com.acalv3.domain.service.strategies.link

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.repository.LinkRepository
import org.springframework.stereotype.Service

@Service
class SaveLinkStrategy(
    val repository: LinkRepository,
): LinkStrategy {

    override fun action() = SAVE

    override fun can(link: Link) {
        repository.findActiveByPlaceId(link.placeId)?.let {
            throw RuntimeException("$MESSAGE${it.customer?.name}")
        }
    }

    companion object{
        private const val MESSAGE = "A ligação não pode ser realizada. o enderço já está associado a outro cliente: "
    }
}