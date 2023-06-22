package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.commons.reference
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import org.springframework.stereotype.Service

@Service
class SaveQualityStrategy(
    val repository: QualityRepository,
): QualityStrategy<Quality> {

    override fun action() = SAVE

    override fun can(model: Quality) {
        repository.findByStartedAt(model.reference)?.let {
            throw DuplicatedFieldException("$ERROR_MESSAGE ${model.reference.reference()}")
        }
    }

    companion object{
        const val ERROR_MESSAGE = "Já existe um coleta cadastrada para a referência:"
    }
}