package br.com.acalv3.application.comunicate.gateway

import br.com.acalv3.application.comunicate.model.request.group.GroupPageRequest
import br.com.acalv3.application.comunicate.model.request.group.GroupSaveRequest
import br.com.acalv3.application.comunicate.model.request.group.GroupUpdateRequest
import br.com.acalv3.application.comunicate.model.request.group.toGroup
import br.com.acalv3.application.comunicate.model.request.group.toGroupPage
import br.com.acalv3.application.comunicate.model.response.group.GroupGetResponse
import br.com.acalv3.application.comunicate.model.response.group.GroupPageResponse
import br.com.acalv3.application.comunicate.model.response.group.SaveUpdateGroupResponse
import br.com.acalv3.application.comunicate.model.response.group.toGetGroupResponse
import br.com.acalv3.application.comunicate.model.response.group.toGroupPageResponse
import br.com.acalv3.application.comunicate.model.response.group.toGroupResponse
import br.com.acalv3.domain.service.GroupService
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
@RequestMapping("group",
    produces=[ "application/json" ],
)
class GroupGateway(
    val service: GroupService,
) {

    @GetMapping("/{id}")
    fun find(@PathVariable id: String): GroupGetResponse =
        service.getById(id).toGetGroupResponse()

    @PostMapping
    fun save(@Valid @RequestBody request: GroupSaveRequest): SaveUpdateGroupResponse =
        service.save(request.toGroup()).toGroupResponse()

    @PutMapping("/update")
    fun update(@Valid @RequestBody request: GroupUpdateRequest): SaveUpdateGroupResponse =
        service.update(request.toGroup()).toGroupResponse()

    @PostMapping("/paginate")
    fun paginate(@Valid @RequestBody request: GroupPageRequest): Page<GroupPageResponse> =
        service.paginate(request.toGroupPage()).toGroupPageResponse()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = service.delete(id)

}