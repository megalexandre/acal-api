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
import br.com.acalv3.application.comunication.model.response.hydrometer.response.toPageResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.toResponse
import br.com.acalv3.application.comunication.model.response.hydrometer.response.toSaveResponse
import br.com.acalv3.application.comunication.model.response.link.LinkGetResponse
import br.com.acalv3.application.comunication.model.response.link.toLinkGetResponse
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.LinkService
import java.util.UUID
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
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
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

   @PostMapping(PAGINATE)
   fun paginate(@Valid @RequestBody request: HydrometerPageRequest): Page<HydrometerPageResponse> =
       service.paginate(request.toPage()).toPageResponse().also {
           logger.info("hydrometer paginate: $request")
       }

   @GetMapping(BY_ID)
   fun find(@PathVariable id: String): HydrometerResponse =
       service.getById(id).toResponse().also {
           logger.info("hydrometer getById: $id")
       }

   @PostMapping
   fun save(@Valid @RequestBody request: List<HydrometerSaveRequest>) =
       request.forEach{
           service.save(it.toDomain()).toSaveResponse()
       }.also {
           logger.info("hydrometer save: $request")
       }

   @GetMapping( "/findByReference/{reference}")
   fun validHydrometerByReference(@PathVariable reference: String): List<LinkGetResponse>? =
        linkService.findHydrometerByReference(reference)?.toLinkGetResponse().also {
            logger.info("hydrometer findHydrometerByReference: $reference")
        }

   @GetMapping("/link/{link}/reference/{reference}")
   fun getHydrometerByLinkAndReference(@PathVariable reference: String, @PathVariable link: UUID): HydrometerResponse? =
       service.getHydrometerByLinkAndReference(linkId = link, reference = reference)?.toResponse().also {
           logger.info("hydrometer getHydrometerByLinkAndReference: $reference, $link")
       }

   @DeleteMapping("{id}")
   fun delete(@PathVariable id: String) =
        service.delete(id).also {
            logger.info("hydrometer delete: $id")
        }
}