package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import org.springframework.stereotype.Service

@Service
class SaveQualityStrategy(
    val repository: QualityRepository,
): QualityStrategy<Quality> {

    override fun action() = SAVE

    override fun can(model: Quality) {
        repository.findByStartedAt(model.startedAt)?.let {
            throw RuntimeException("$ERROR_MESSAGE ${model.startedAt}")
        }
    }

    companion object{
        private const val ERROR_MESSAGE = "Já existe um coleta cadastrada para a referencia: "
    }
}