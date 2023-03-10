package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.resources.model.business.toQuality
import br.com.acalv3.resources.model.business.toQualityEntity
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.QualityRepositoryJpa
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
class QualityRepositoryImpl(
    private val repository: QualityRepositoryJpa,
) : QualityRepository, DefaultRepository {

    override fun getById(id: String): Quality = repository.getById(UUID.fromString(id)).toQuality()

    override fun save(quality: Quality): Quality =
        repository.save(quality.toQualityEntity()).toQuality()

}
