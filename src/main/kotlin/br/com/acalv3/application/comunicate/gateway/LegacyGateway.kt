package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.domain.service.LegacyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/legacy")
class LegacyGateway(
    val legacyService: LegacyService
) {

    @GetMapping("/person")
    fun status() =
        legacyService.person()

    @GetMapping("/address")
    fun address() =
        legacyService.address()

}