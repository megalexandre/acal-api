package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.application.comunicate.model.request.link.LinkSaveRequest
import br.com.acalv3.application.comunicate.model.request.link.LinkUpdateRequest
import br.com.acalv3.application.comunicate.model.request.link.toLink
import br.com.acalv3.application.comunicate.model.request.link.toPageRequest
import br.com.acalv3.application.comunicate.model.response.link.LinkGetResponse
import br.com.acalv3.application.comunicate.model.response.link.LinkPageResponse
import br.com.acalv3.application.comunicate.model.response.link.SaveUpdateLinkResponse
import br.com.acalv3.application.comunicate.model.response.link.toLinkGetResponse
import br.com.acalv3.application.comunicate.model.response.link.toLinkPageResponse
import br.com.acalv3.application.comunicate.model.response.link.toLinkResponse
import br.com.acalv3.application.comunicate.model.response.place.SaveUpdatePlaceResponse
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.PlaceService
import javax.validation.Valid
import org.springframework.data.domain.Page
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
    produces=[ "application/json" ],
)
class LinkGateway(
    val service: LinkService,
    val customerService: CustomerService,
    val placeService: PlaceService,
    val groupService: GroupService,
) {

    @PostMapping
    fun save(@RequestBody request: LinkSaveRequest): SaveUpdateLinkResponse = run {
        service.save(request.toLink(
            customer = customerService.getById(request.customerId()),
            place = placeService.getById(request.placeId()),
            mailPlace = placeService.getById(request.mailPlaceId()),
            group = groupService.getById(request.groupId()),
        )).toLinkResponse()
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: LinkUpdateRequest): SaveUpdateLinkResponse = run {
        service.update(
            request.toLink(
                customerService.getById(request.customer?.id ?: throw RuntimeException(""))
            )).toLinkResponse()
    }

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: LinkPageRequest): Page<LinkPageResponse> =
        service.paginate(request.toPageRequest()).toLinkPageResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): LinkGetResponse = service.getById(id).toLinkGetResponse()

    @DeleteMapping("inactive/{id}")
    fun inactivate(@PathVariable id: String) = service.inactivate(id)

}