package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Hydrometer
import org.springframework.stereotype.Service

@Service
class UpdateHydrometerStrategy : HydrometerStrategy<Hydrometer> {

    override fun action() = UPDATE

    override fun can(model: Hydrometer) {}

}