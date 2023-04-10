package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Hydrometer
import org.springframework.stereotype.Service

@Service
class DeleteHydrometerStrategy: HydrometerStrategy<Hydrometer> {

    override fun action() = DELETE

    override fun can(model: Hydrometer) {}

}