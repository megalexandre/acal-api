package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface HydrometerStrategy<Q: Hydrometer> : AbstractStrategy<Hydrometer> {
    override fun action(): Action
    override fun can(model: Hydrometer)
}