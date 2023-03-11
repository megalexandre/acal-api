package br.com.acalv3.domain.service.strategies.group

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.repository.GroupRepository
import org.springframework.stereotype.Service

@Service
class UpdateGroupStrategy(
    val repository: GroupRepository,
): GroupStrategy<Group> {

    override fun action() = UPDATE

    override fun can(group: Group) {
        repository.findGroup(group)?.let {
            if(it.id != group.id){
                throw RuntimeException("O grupo [${it.name}] já está cadastrado para a categoria [${group.category.value}]")
            }
        }
    }

}