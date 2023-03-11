package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface QualityStrategy<Q: Quality> : AbstractStrategy<Quality> {
    override fun action(): Action
    override fun can(model: Quality)
}