package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Place
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.PlaceRepository
import br.com.acalv3.domain.repository.QualityRepository
import org.springframework.stereotype.Service

@Service
class SaveQualityStrategy(
    val repository: QualityRepository,
): QualityStrategy {

    override fun action() = SAVE

    override fun can(quality: Quality) {
    }
}