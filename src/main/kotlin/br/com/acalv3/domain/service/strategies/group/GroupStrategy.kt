package br.com.acalv3.domain.service.strategies.group

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface GroupStrategy<Q: Group> : AbstractStrategy<Group> {
    override fun action(): Action
    override fun can(group: Group)
}