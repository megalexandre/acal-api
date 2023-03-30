package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.response.dashboard.DashboardResponse
import br.com.acalv3.application.comunicate.model.response.dashboard.toDashboardResponse
import br.com.acalv3.domain.service.DashboardService
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("dashboard",
    produces=[APPLICATION_JSON_VALUE],
)
class DashboardGateway(
    val service: DashboardService,
) {
    @GetMapping
    fun get(): DashboardResponse = service.get().toDashboardResponse()
}