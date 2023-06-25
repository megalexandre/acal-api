package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.repository.HydrometerRepository
import org.springframework.stereotype.Service

@Service
class SaveHydrometerStrategy(
    val repository: HydrometerRepository,
) : HydrometerStrategy<Hydrometer> {

    override fun action() = SAVE

    override fun can(model: Hydrometer) {
        repository.getHydrometerByLinkAndReference(linkId = model.linkId,reference = model.reference)?.let{
            throw RuntimeException(String.format(ERROR_MESSAGE, model.reference))
        }
    }

    companion object{
        private const val ERROR_MESSAGE = "Já existe um registro cadastrados para essa referência"
    }
}