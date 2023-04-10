package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.HEALTH
import br.com.acalv3.application.comunicate.model.response.health.HealthResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(HEALTH, produces=[MediaType.APPLICATION_JSON_VALUE])
class HealthGateway(
    @Value("\${app.version}")
    val version: String
) {

    @GetMapping("/status")
    fun status(): HealthResponse = HealthResponse(version = version)
}