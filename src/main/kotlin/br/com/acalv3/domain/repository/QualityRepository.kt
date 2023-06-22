package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.QualityPage

interface QualityRepository: AbstractRepository<Quality, QualityPage>{
    fun findByStartedAt(startedAt: String): Quality?
    fun findByReferenceIn(references: List<String>): List<Quality>?
}