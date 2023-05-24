package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.page.HydrometerPage
import br.com.acalv3.domain.repository.HydrometerRepository
import br.com.acalv3.resources.model.business.toDomain
import br.com.acalv3.resources.model.business.toDomainWithoutLink
import br.com.acalv3.resources.model.business.toEntity
import br.com.acalv3.resources.model.business.toPage
import br.com.acalv3.resources.repository.interfaces.HydrometerRepositoryJpa
import br.com.acalv3.resources.repository.specification.HydrometerSpecification
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class HydrometerRepositoryImp(
    private val repository: HydrometerRepositoryJpa,
) : HydrometerRepository {

    override fun getHydrometerByLinkAndReference(linkId: UUID, reference: String): Hydrometer? =
        repository.findByReferenceAndLinkId(reference = reference, id = linkId)?.toDomainWithoutLink()

    override fun getById(id: String): Hydrometer =
        repository.findByIdOrNull(UUID.fromString(id))?.toDomain() ?: throw NotFoundException()

    override fun save(type: Hydrometer): Hydrometer = repository.save(type.toEntity()).toDomain()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun paginate(page: HydrometerPage): Page<Hydrometer> =
        repository.findAll(
            HydrometerSpecification(page).getSpecification(),
            super.pageable(page)
        ).toPage()

    override fun findAll(page: HydrometerPage): List<Hydrometer> =
        repository.findAll(HydrometerSpecification(page).getSpecification()).toDomain()

    override fun findAll(): List<Hydrometer> = repository.findAll().toDomain()

    override fun count(): Long = repository.count()

    override fun getAll(): List<Hydrometer> = repository.findAll().toDomain()



}
