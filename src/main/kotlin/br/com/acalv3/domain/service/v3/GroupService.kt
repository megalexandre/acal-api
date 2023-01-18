package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.model.v3.GroupModel
import br.com.acalv3.domain.repository.v3.GroupRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.GroupSpec
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class GroupService(
	val repository: GroupRepository,
	val appSpec: JpaSpecificationExecutor<GroupModel>,
): AppService<GroupModel>(repository, repository) {

	override fun findByName(@PathVariable name: String): GroupModel =
		repository.findByName(name)

	override fun pageable(filter: FilterDTO<GroupModel>)  =
		repository.findAll(

			GroupSpec(
				filter.model
			),

			getPage(filter)
		)

	override fun filterByExample(filter: FilterDTO<GroupModel>): List<GroupModel> {

		val sort = Sort.by(Sort.Direction.ASC, "name")
		val spec = GroupSpec(filter.model)

		return appSpec.findAll(spec, sort)
	}
}
