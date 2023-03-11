package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.domain.service.strategies.quality.QualityStrategy

abstract class QualityService: AbstractService<Quality, QualityPage>() {
    abstract override fun strategies(): List<QualityStrategy<Quality>>
    abstract override fun repository(): QualityRepository
}
