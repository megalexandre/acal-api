package br.com.acalv3.application.comunication.model.request.group

import br.com.acalv3.domain.enumeration.Category
import br.com.acalv3.domain.model.Group
import java.util.UUID

class GroupSaveRequest: GroupRequest()

fun GroupSaveRequest.toGroup() = Group(
    id = UUID.randomUUID(),
    name = name?.trim() ?: throw RuntimeException("name can't be null"),
    value =  value ?: throw RuntimeException("name can't be null"),
    category = Category.byValue(category) ?: throw RuntimeException("category can't be null"),
    categoryValue = categoryValue ?: throw RuntimeException("categoryValue can't be null"),
)