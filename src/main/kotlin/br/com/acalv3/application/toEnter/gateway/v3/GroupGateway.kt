package br.com.acalv3.application.toEnter.gateway.v3

import br.com.acalv3.application.toEnter.gateway.AppGateway
import br.com.acalv3.domain.model.v3.GroupModel
import br.com.acalv3.domain.service.v3.GroupService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("group")
class GroupGateway(
	groupService: GroupService
): AppGateway<GroupModel>(groupService)