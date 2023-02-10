package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.domain.service.GroupService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class GroupServiceImpl(
	val repository: GroupRepository,
): GroupService {

	override fun getById(id: String): Group =
		repository.getById(id)

	override fun save(group: Group): Group =
		repository.save(group)

	override fun update(group: Group): Group =
		repository.save(group)

	override fun findByName(name: String): Group =
        repository.findByName(name)

	override fun paginate(pageRequest: GroupPage): Page<Group> =
		repository.paginate(pageRequest)

	override fun getAll(): List<Group> =
		repository.getAll()
}
