package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.BY_ID
import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.HYDROMETER
import br.com.acalv3.application.comunicate.GatewaysRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunicate.model.request.hydrometer.HydrometerPageRequest
import br.com.acalv3.application.comunicate.model.request.hydrometer.HydrometerSaveRequest
import br.com.acalv3.application.comunicate.model.request.hydrometer.toDomain
import br.com.acalv3.application.comunicate.model.request.hydrometer.toPage
import br.com.acalv3.application.comunicate.model.response.hydrometer.response.HydrometerPageResponse
import br.com.acalv3.application.comunicate.model.response.hydrometer.response.HydrometerSaveResponse
import br.com.acalv3.application.comunicate.model.response.hydrometer.response.toPageResponse
import br.com.acalv3.application.comunicate.model.response.hydrometer.response.toResponse
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.LinkService
import javax.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(HYDROMETER, produces=[APPLICATION_JSON_VALUE])
class HydrometerGateway(
    val service: HydrometerService,
    val linkService: LinkService
) {

   @PostMapping(PAGINATE)
   fun paginate(@Valid @RequestBody request: HydrometerPageRequest): Page<HydrometerPageResponse> =
       service.paginate(request.toPage()).toPageResponse()

   @GetMapping(BY_ID)
   fun find(@PathVariable id: String): HydrometerSaveResponse = service.getById(id).toResponse()

   @PostMapping
   fun save(@Valid @RequestBody request: HydrometerSaveRequest): HydrometerSaveResponse =
        service.save(request.toDomain(
            linkService.getById(request.linkId.toString())
        )).toResponse()
}