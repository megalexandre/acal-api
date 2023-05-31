package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.repository.HydrometerRepository
import org.springframework.stereotype.Service

@Service
class UpdateHydrometerStrategy(
    val repository: HydrometerRepository,
) : HydrometerStrategy<Hydrometer> {

    override fun action() = UPDATE

    override fun can(model: Hydrometer) {
        repository.findByReference(model.reference).let {
            if(it === null)
            throw RuntimeException(String.format("", model.reference))
        }
    }

}