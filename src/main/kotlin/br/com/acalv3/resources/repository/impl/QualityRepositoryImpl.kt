package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.resources.model.business.toPage
import br.com.acalv3.resources.model.business.toQuality
import br.com.acalv3.resources.model.business.toQualityEntity
import br.com.acalv3.resources.repository.interfaces.QualityRepositoryJpa
import br.com.acalv3.resources.repository.specification.QualitySpecification
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.stereotype.Repository

@Repository
class QualityRepositoryImpl(
    private val repository: QualityRepositoryJpa,
) : QualityRepository {

    override fun getById(id: String): Quality = repository.getById(UUID.fromString(id)).toQuality()
    override fun save(quality: Quality): Quality = repository.save(quality.toQualityEntity()).toQuality()
    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))
    override fun count(): Long = repository.count()
    override fun getAll(): List<Quality> = repository.findAll().toQuality()

    override fun paginate(page: QualityPage): Page<Quality> =
        repository.findAll(
            QualitySpecification(page).getSpecification(),
            pageable(page)
        ).toPage()

    override fun findAll(page: QualityPage): List<Quality> =
        repository.findAll(
            QualitySpecification(page).getSpecification(),
        ).toQuality()

}
