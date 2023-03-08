package br.com.acalv3.application.comunicate.gateway

import java.time.LocalDateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthGateway(
    @Value("\${app.version}")
    val version: String
) {

    @GetMapping("/status")
    fun status() = "time=${LocalDateTime.now()} version:${version}"

}