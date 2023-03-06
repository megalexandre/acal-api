package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.strategies.customer.CustomerStrategy
import br.com.acalv3.domain.service.strategies.group.GroupStrategy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(
	val repository: GroupRepository,
	val strategies: List<GroupStrategy>
): GroupService {

	override fun getById(id: String): Group = repository.getById(id)

	override fun save(group: Group): Group =
		strategies.first{ it.action() == SAVE }.can(group).let {
			repository.save(group)
		}

	override fun update(group: Group): Group =
		strategies.first{ it.action() == UPDATE }.can(group).let {
			repository.save(group)
		}

	override fun delete(id: String) =
		strategies.first{ it.action() == DELETE }.can(repository.getById(id)).let {
			repository.delete(id)
		}

	override fun findByName(name: String): Group =  repository.findByName(name)

	override fun paginate(pageRequest: GroupPage): Page<Group> = repository.paginate(pageRequest)

	override fun getAll(): List<Group> = repository.getAll()


}
