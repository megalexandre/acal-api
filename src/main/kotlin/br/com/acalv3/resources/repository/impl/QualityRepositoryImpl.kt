package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.resources.model.business.toDomain
import br.com.acalv3.resources.model.business.toEntity
import br.com.acalv3.resources.model.business.toGathering
import br.com.acalv3.resources.model.business.toGatheringEntity
import br.com.acalv3.resources.model.business.toPage
import br.com.acalv3.resources.repository.interfaces.GatheringRepositoryJpa
import br.com.acalv3.resources.repository.interfaces.QualityRepositoryJpa
import br.com.acalv3.resources.repository.specification.QualitySpecification
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
class QualityRepositoryImpl(
    private val repository: QualityRepositoryJpa,
    private val gatheringRepository: GatheringRepositoryJpa,
) : QualityRepository {

    override fun findByStartedAt(reference: String) =
        repository.findByReference(reference)?.toDomain()

    override fun findByReferenceIn(references: List<String>): List<Quality>? = repository.findByReferenceIn(references)?.toDomain()
    override fun getById(id: String): Quality = repository.getById(UUID.fromString(id)).toDomain()

    override fun save(type: Quality): Quality {
        val gathering = type.gathering

       val quality = repository.save(
           type.copy(
               gathering = null
           ).toEntity()).toDomain()

            gathering?.let {
                it.forEach {gathering ->
                    gatheringRepository.save(gathering.copy(id = UUID.randomUUID()).toGatheringEntity()).toGathering()
                }
            }

        return quality
    }


    override fun saveAll(type: List<Quality>) {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))
    override fun count(): Long = repository.count()

    override fun paginate(page: QualityPage): Page<Quality> =
        repository.findAll(
            QualitySpecification(page).getSpecification(),
            pageable(page)
        ).toPage()

    override fun findAll(page: QualityPage): List<Quality> =
        repository.findAll(
            QualitySpecification(page).getSpecification(),
        ).toDomain()

    override fun findAll(): List<Quality> = repository.findAll().toDomain()

}
