package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.link.LinkPageRequest
import br.com.acalv3.application.comunicate.model.request.link.LinkSaveRequest
import br.com.acalv3.application.comunicate.model.request.link.LinkUpdateRequest
import br.com.acalv3.application.comunicate.model.request.link.toLink
import br.com.acalv3.application.comunicate.model.response.link.*
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.LinkService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("link", produces=[ "application/json" ])
class LinkGateway(
    val service: LinkService,
    val customerService: CustomerService
) {

    @PostMapping
    fun save(@Valid @RequestBody request: LinkSaveRequest): SaveUpdateLinkResponse = run {
        val customer = customerService.getById(UUID.fromString(request.customer))
        service.save(request.toLink(customer)).toLinkResponse()
    }

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: LinkUpdateRequest): SaveUpdateLinkResponse = run {
        service.update(request.toLink(customerService.getById(UUID.fromString(request.customerEntity)))).toLinkResponse()
    }

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: LinkPageRequest): Page<LinkPageResponse> =
        service.paginate(request).toLinkPageResponse()

    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): LinkGetResponse =
        service.getById(id).toLinkGetResponse()

}