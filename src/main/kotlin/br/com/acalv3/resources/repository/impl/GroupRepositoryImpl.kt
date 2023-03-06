package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.model.page.GroupPage
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.resources.model.business.toGroup
import br.com.acalv3.resources.model.business.toGroupEntity
import br.com.acalv3.resources.model.business.toGroupPage
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.GroupRepositoryJpa
import br.com.acalv3.resources.repository.specification.GroupSpecification
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class GroupRepositoryImpl(
    private val repository: GroupRepositoryJpa,
) : GroupRepository, DefaultRepository {

    override fun getById(id: String): Group =
        repository.findByIdOrNull(UUID.fromString(id))?.toGroup() ?: throw NotFoundException()

    override fun findGroup(group: Group): Group? =
        repository.findAll(
            GroupSpecification(
                GroupPage(
                    name = group.name,
                    category = group.category
        )).getSpecification()).firstOrNull()?.toGroup()

    override fun findByName(name: String): Group =
       repository.findByName(name).toGroup()

    override fun save(group: Group): Group =
        repository.save(group.toGroupEntity()).toGroup()

    override fun update(group: Group): Group =
        repository.save(group.toGroupEntity()).toGroup()

    override fun paginate(request: GroupPage): Page<Group> =
        repository.findAll(
            GroupSpecification(request).getSpecification(),
            super.pageable(request)
        ).toGroupPage()

    override fun getAll(): List<Group> = repository.findAll().toGroup()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

}
