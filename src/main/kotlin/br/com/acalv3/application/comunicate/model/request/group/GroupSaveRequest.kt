package br.com.acalv3.application.comunicate.model.request.group

import br.com.acalv3.domain.model.Group
import java.util.*

class GroupSaveRequest: GroupRequest()

fun GroupSaveRequest.toGroup() = Group(
    id = UUID.randomUUID().toString(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    value =  value ?: throw RuntimeException("name can't be null"),
    category =  category?.trim() ?: throw RuntimeException("category can't be null"),
)