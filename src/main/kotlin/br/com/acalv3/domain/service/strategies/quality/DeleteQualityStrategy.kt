package br.com.acalv3.domain.service.strategies.quality

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Quality
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DeleteQualityStrategy: QualityStrategy<Quality> {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun action() = DELETE

    override fun can(model: Quality) {
        logger.info("delete quality")
    }

}