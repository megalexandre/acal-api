package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.page.HydrometerPage
import java.util.UUID

interface HydrometerRepository:  AbstractRepository<Hydrometer, HydrometerPage>{
    fun getHydrometerByLinkAndReference(linkId: UUID, reference: String): Hydrometer?
    fun findByReference(reference: String): Hydrometer?
}