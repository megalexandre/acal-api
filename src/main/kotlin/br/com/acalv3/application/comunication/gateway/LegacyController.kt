package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.domain.service.LegacyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/legacy")
class LegacyController(
    val legacyService: LegacyService
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/person")
    fun status() =
        legacyService.person().also{
            logger.info("legacy status")
        }

    @GetMapping("/address")
    fun address() =
        legacyService.address().also{
            logger.info("legacy address")
        }

}