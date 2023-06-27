package br.com.acalv3.domain.service.impl

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.repository.HydrometerRepository
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.strategies.hydrometer.HydrometerStrategy
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class HydrometerServiceImpl(
	val repository: HydrometerRepository,
	val strategies: List<HydrometerStrategy<Hydrometer>>
): HydrometerService() {
	override fun strategies(): List<HydrometerStrategy<Hydrometer>> = strategies
	override fun repository(): HydrometerRepository = repository

	override fun getHydrometerByLinkAndReference(linkId: UUID, reference: String): Hydrometer? =
	 	repository.getHydrometerByLinkAndReference(linkId, reference)

	override fun getHydrometerByReference(reference: String): List<Hydrometer>? =
		repository.findByReference(reference)

}