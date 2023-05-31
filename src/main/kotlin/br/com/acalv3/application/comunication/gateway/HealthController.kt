package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.HEALTH
import br.com.acalv3.application.comunication.model.response.health.HealthResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(HEALTH, produces=[MediaType.APPLICATION_JSON_VALUE])
class HealthController(
    @Value("\${app.version}")
    val version: String
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/status")
    fun status(): HealthResponse = HealthResponse(version = version).also {
        logger.info("getting status")
    }

}