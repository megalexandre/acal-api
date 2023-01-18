package br.com.acalv3.application.toEnter.gateway.v3

import br.com.acalv3.application.toEnter.gateway.AppGateway
import br.com.acalv3.domain.model.v3.PlaceModel
import br.com.acalv3.domain.service.v3.PlaceService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("place")
class PlaceGateway(
    service: PlaceService
): AppGateway<PlaceModel>(service)