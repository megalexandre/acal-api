package br.com.acalv3.application.comunication.model.response.group

import br.com.acalv3.domain.model.Group
import java.util.UUID

class SaveUpdateGroupResponse(
    val id: UUID
)

fun Group.toGroupResponse() = SaveUpdateGroupResponse(
    id = id
)