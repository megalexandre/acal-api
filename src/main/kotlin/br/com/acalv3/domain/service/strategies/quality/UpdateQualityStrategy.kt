package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import org.springframework.stereotype.Service

@Service
class UpdateQualityStrategy(
    val repository: QualityRepository,
): QualityStrategy {

    override fun action() = UPDATE

    override fun can(quality: Quality) {
    }

}