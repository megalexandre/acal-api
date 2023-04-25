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
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.PlaceService
import javax.validation.Valid
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
    val customerService: CustomerService,
    val placeService: PlaceService,
    val groupService: GroupService,
) {

    @GetMapping("/hydrometer/{reference}")
    fun linkWithHydrometerByMonth(@PathVariable reference: String) = service.linkWithHydrometerByMonth(reference)

    @PostMapping
    fun save(@RequestBody request: LinkSaveRequest): SaveUpdateLinkResponse =
        service.save(request.toLink(
            customer = customerService.getById(request.customerId()),
            place = placeService.getById(request.placeId()),
            mailPlace = placeService.getById(request.mailPlaceId()),
            group = groupService.getById(request.groupId()),
        )).toLinkResponse()

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: LinkUpdateRequest): SaveUpdateLinkResponse =
        service.update(
            request.toLink(
                customerService.getById(request.customer?.id ?: throw RuntimeException(""))
            )).toLinkResponse()

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: LinkPageRequest): Page<LinkPageResponse> =
        service.paginate(request.toPageRequest()).toLinkResponse()

    @GetMapping("/list")
    fun findAll(): List<LinkPageResponse> =
        service.findAll().toLinkResponse()

    @GetMapping("/list/{reference}")
    fun findAllByReference(@PathVariable reference: String): List<LinkPageResponse> =
        service.findAll(reference).toLinkResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): LinkGetResponse = service.getById(id).toLinkGetResponse()

    @DeleteMapping("inactive/{id}")
    fun inactivate(@PathVariable id: String) = service.inactivate(id)

    @PostMapping(path = ["/report"], produces = [APPLICATION_PDF_VALUE])
    fun link(@RequestBody request: LinkPageRequest): ByteArray? =
        service.report(request.toPageRequest())

}