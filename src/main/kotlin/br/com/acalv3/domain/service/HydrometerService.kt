package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.page.HydrometerPage
import br.com.acalv3.domain.repository.HydrometerRepository
import br.com.acalv3.domain.service.strategies.hydrometer.HydrometerStrategy
import java.util.UUID

abstract class HydrometerService: AbstractService<Hydrometer, HydrometerPage>() {
    abstract override fun strategies(): List<HydrometerStrategy<Hydrometer>>
    abstract override fun repository(): HydrometerRepository

    abstract fun getHydrometerByLinkAndReference(linkId: UUID, reference: String): Hydrometer?
    abstract fun getHydrometerByReference(reference: String): List<Hydrometer>?
}
