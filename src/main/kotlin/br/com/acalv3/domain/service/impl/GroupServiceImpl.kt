package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.strategies.group.GroupStrategy
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(
	val repository: GroupRepository,
	val strategies: List<GroupStrategy<Group>>
): GroupService() {

	override fun strategies(): List<GroupStrategy<Group>> = strategies
	override fun repository(): GroupRepository = repository

	override fun findByName(name: String): Group = repository.findByName(name)

}
