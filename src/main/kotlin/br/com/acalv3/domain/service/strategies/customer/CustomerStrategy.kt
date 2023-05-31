package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface CustomerStrategy<Q: Any> : AbstractStrategy<Customer>  {
    override fun action(): Action
    override fun can(model: Customer)
}
