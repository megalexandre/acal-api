package br.com.acalv3.application.comunicate.model.response.health

import java.time.LocalDateTime

class HealthResponse (
    val time: LocalDateTime = LocalDateTime.now(),
    val version: String
)
