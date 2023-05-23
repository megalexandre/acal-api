package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.ControllersRoutes.Companion.BY_ID
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.HYDROMETER
import br.com.acalv3.application.comunication.ControllersRoutes.Companion.PAGINATE
import br.com.acalv3.application.comunication.model.request.hydrometer.HydrometerPageRequest
import br.com.acalv3.application.comunication.model.request.hydrometer.HydrometerSaveRequest
import br.com.acalv3.application.comunication.model.request.hydrometer.toDomain
import br.com.acalv3.application.comunication.model.request.hydrometer.toPage
import br.com.acalv3.application.comunication.model.response.hydrometer.response.HydrometerPageResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.HydrometerResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.HydrometerSaveResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.toPageResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.toResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.toSaveResponse
import br.com.acalv3.application.comunication.model.response.link.LinkGetResponse
import br.com.acalv3.application.comunication.model.response.link.toLinkGetResponse
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
class HydrometerController(
    val service: HydrometerService,
    val linkService: LinkService
) {

   @PostMapping(PAGINATE)
   fun paginate(@Valid @RequestBody request: HydrometerPageRequest): Page<HydrometerPageResponse> =
       service.paginate(request.toPage()).toPageResponse()

   @GetMapping(BY_ID)
   fun find(@PathVariable id: String): HydrometerResponse =
       service.getById(id).toResponse()

   @PostMapping
   fun save(@Valid @RequestBody request: HydrometerSaveRequest): HydrometerSaveResponse =
        service.save(request.toDomain(linkService.getById(request.linkId.toString()))).toSaveResponse()

    @PostMapping("lote")
    fun saveAll(@Valid @RequestBody request: List<HydrometerSaveRequest>) =
        request.forEach{
            service.save(it.toDomain(linkService.getById(it.linkId.toString())))
        }

    @GetMapping( "/findByReference/{reference}")
    fun validHydrometerByReference(@PathVariable reference: String): List<LinkGetResponse>? =
        linkService.findHydrometerByReference(reference)?.toLinkGetResponse()
}