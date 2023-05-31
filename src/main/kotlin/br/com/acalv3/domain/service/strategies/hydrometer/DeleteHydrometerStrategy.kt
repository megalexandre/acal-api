package br.com.acalv3.domain.service.strategies.hydrometer

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Hydrometer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DeleteHydrometerStrategy: HydrometerStrategy<Hydrometer> {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun action() = DELETE

    override fun can(model: Hydrometer) {
        logger.info("delete hydrometer")
    }

}