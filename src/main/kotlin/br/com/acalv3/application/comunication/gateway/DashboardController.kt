package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.DASHBOARD
import br.com.acalv3.application.comunication.model.response.dashboard.DashboardResponse
import br.com.acalv3.application.comunication.model.response.dashboard.toDashboardResponse
import br.com.acalv3.domain.service.DashboardService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(DASHBOARD,
    produces=[APPLICATION_JSON_VALUE],
)
class DashboardController(
    val service: DashboardService,
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun get(): DashboardResponse = service.get().toDashboardResponse().also {
        logger.info("getting dashboard")
    }
}