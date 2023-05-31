package br.com.acalv3.domain.service.strategies.group

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Group
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface GroupStrategy<Q: Any> : AbstractStrategy<Group> {
    override fun action(): Action
    override fun can(model: Group)
}