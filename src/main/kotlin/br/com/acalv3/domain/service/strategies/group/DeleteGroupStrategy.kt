package br.com.acalv3.domain.service.strategies.group

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.domain.service.LinkService
import org.springframework.stereotype.Service

@Service
class DeleteGroupStrategy(
    val repository: GroupRepository,
    val linkService: LinkService
): GroupStrategy {

    override fun action() = DELETE

    override fun can(group: Group) {
        linkService.findByGroupId(group.id)?.let { it ->
            throw RuntimeException("O grupo não pode ser apagado porque está associado as ligações: " +
                "${it.group.name} de categoria ${ it.group.category.value}"
            )
        }
    }

}