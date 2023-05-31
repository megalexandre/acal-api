package br.com.acalv3.application.comunication.gateway

import br.com.acalv3.application.comunication.model.request.link.LinkPageRequest
import br.com.acalv3.application.comunication.model.request.link.LinkSaveRequest
import br.com.acalv3.application.comunication.model.request.link.LinkUpdateRequest
import br.com.acalv3.application.comunication.model.request.link.toLink
import br.com.acalv3.application.comunication.model.request.link.toPageRequest
import br.com.acalv3.application.comunication.model.response.link.LinkGetResponse
import br.com.acalv3.application.comunication.model.response.link.LinkPageResponse
import br.com.acalv3.application.comunication.model.response.link.SaveUpdateLinkResponse
import br.com.acalv3.application.comunication.model.response.link.toLinkGetResponse
import br.com.acalv3.application.comunication.model.response.link.toLinkResponse
import br.com.acalv3.domain.service.LinkService
import javax.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.APPLICATION_PDF_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("link",
    produces=[APPLICATION_JSON_VALUE],
)
class LinkController(
    val service: LinkService,
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/hydrometer/{reference}")
    fun linkWithHydrometerByMonth(@PathVariable reference: String) =
        service.linkWithHydrometerByMonth(reference).also {
            logger.info("linkWithHydrometerByMonth: $reference")
        }

    @PostMapping
    fun save(@RequestBody request: LinkSaveRequest): SaveUpdateLinkResponse =
        service.save(request.toLink()).toLinkResponse().also {
            logger.info("save: $request")
        }

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: LinkUpdateRequest): SaveUpdateLinkResponse =
        service.update(request.toLink()).toLinkResponse().also {
            logger.info("update: $request")
        }

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: LinkPageRequest): Page<LinkPageResponse> =
        service.paginate(request.toPageRequest()).toLinkResponse().also {
            logger.info("paginate: $request")
        }

    @GetMapping("/list")
    fun findAll(): List<LinkPageResponse> =
        service.findAll().toLinkResponse().also {
            logger.info("findAll")
        }

    @GetMapping("/list/{reference}")
    fun findAllByReference(@PathVariable reference: String): List<LinkPageResponse> =
        service.findAll(reference).toLinkResponse().also {
            logger.info("findAll: $reference")
        }

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): LinkGetResponse =
        service.getById(id).toLinkGetResponse().also {
            logger.info("getById: $id")
        }

    @DeleteMapping("inactive/{id}")
    fun inactivate(@PathVariable id: String) =
        service.inactivate(id).also {
            logger.info("inactivate: $id")
        }

    @PostMapping(path = ["/report"], produces = [APPLICATION_PDF_VALUE])
    fun link(@RequestBody request: LinkPageRequest): ByteArray? =
        service.report(request.toPageRequest()).also {
            logger.info("report: $request")
        }

}