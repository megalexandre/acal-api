package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.domain.service.QualityService
import br.com.acalv3.domain.service.strategies.quality.QualityStrategy
import org.springframework.stereotype.Service

@Service
class QualityServiceImpl(
	val repository: QualityRepository,
	val strategies: List<QualityStrategy<Quality>>
): QualityService() {

	override fun strategies(): List<QualityStrategy<Quality>> = strategies
	override fun repository(): QualityRepository = repository

}
