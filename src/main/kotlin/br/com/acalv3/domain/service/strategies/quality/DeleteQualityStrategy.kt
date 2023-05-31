package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Quality
import org.springframework.stereotype.Service

@Service
class QualityQualityStrategy: QualityStrategy<Quality> {

    override fun action() = DELETE

    override fun can(model: Quality) {}

}