package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.model.page.QualityPage
import br.com.acalv3.domain.repository.QualityRepository
import br.com.acalv3.domain.service.QualityService
import br.com.acalv3.domain.service.strategies.quality.QualityStrategy
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class QualityServiceImpl(
	val repository: QualityRepository,
	val strategies: List<QualityStrategy>
): QualityService() {

	override fun strategies(): List<QualityStrategy> = strategies
	override fun repository(): QualityRepository = repository
	override fun paginate(page: QualityPage): Page<Quality> = repository.paginate(page)

}
