package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.DATABASE_ROUTE
import br.com.acalv3.domain.service.DataBaseService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(DATABASE_ROUTE, produces=[APPLICATION_JSON_VALUE])
class DatabaseController(
    val service: DataBaseService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("backup")
    fun backup() = service.backup()

}