package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.GroupRepository
import br.com.acalv3.domain.service.LinkService
import org.springframework.stereotype.Service

@Service
class QualityQualityStrategy: QualityStrategy {

    override fun action() = DELETE

    override fun can(quality: Quality) {}

}