package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.resources.model.business.toDomain
import br.com.acalv3.resources.model.business.toEntity
import br.com.acalv3.resources.model.business.toPage
import br.com.acalv3.resources.repository.interfaces.QualityRepositoryJpa
import br.com.acalv3.resources.repository.specification.QualitySpecification
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
class QualityRepositoryImpl(
    private val repository: QualityRepositoryJpa,
) : QualityRepository {

    override fun findByStartedAt(startedAt: String) =
        repository.findByStartedAt(startedAt)?.toDomain()

    override fun getById(id: String): Quality = repository.getById(UUID.fromString(id)).toDomain()
    override fun save(type: Quality): Quality = repository.save(type.toEntity()).toDomain()
    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))
    override fun count(): Long = repository.count()
    override fun getAll(): List<Quality> = repository.findAll().toDomain()

    override fun paginate(page: QualityPage): Page<Quality> =
        repository.findAll(
            QualitySpecification(page).getSpecification(),
            pageable(page)
        ).toPage()

    override fun findAll(page: QualityPage): List<Quality> =
        repository.findAll(
            QualitySpecification(page).getSpecification(),
        ).toDomain()

    override fun findAll(): List<Quality> {
        TODO("Not yet implemented")
    }

}
