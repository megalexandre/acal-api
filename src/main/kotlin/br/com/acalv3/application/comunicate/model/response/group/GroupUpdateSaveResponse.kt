package br.com.acalv3.application.comunicate.model.response.group

import br.com.acalv3.domain.model.Group

class SaveUpdateGroupResponse(
    val id: String
)

fun Group.toGroupResponse() = SaveUpdateGroupResponse(
    id = id
)