package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Hydrometer
import org.springframework.stereotype.Service

@Service
class SaveHydrometerStrategy(
): HydrometerStrategy<Hydrometer> {

    override fun action() = SAVE

    override fun can(model: Hydrometer) {

    }

    companion object{
        private const val ERROR_MESSAGE = "Já existe um registro cadastrados para essa referência: %f"
    }
}