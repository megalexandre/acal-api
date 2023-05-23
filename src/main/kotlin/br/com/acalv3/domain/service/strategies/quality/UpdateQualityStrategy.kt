package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.commons.reference
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.exception.DuplicatedFieldException
import br.com.acalv3.domain.model.Quality
import br.com.acalv3.domain.repository.QualityRepository
import org.springframework.stereotype.Service

@Service
class UpdateQualityStrategy(
    val repository: QualityRepository,
): QualityStrategy<Quality> {

    override fun action() = UPDATE

    override fun can(quality: Quality) {
        repository.findByStartedAt(quality.startedAt)?.let {
            if(it.id != quality.id){
                throw DuplicatedFieldException("$ERROR_MESSAGE ${quality.startedAt.reference()}")
            }
        }
    }

    companion object{
        private const val ERROR_MESSAGE = "Já existe um coleta cadastrada para a referência:"
    }

}