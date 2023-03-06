package br.com.acalv3.domain.service.strategies.group

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group

interface GroupStrategy  {
    fun action(): Action
    fun can(group: Group)
}