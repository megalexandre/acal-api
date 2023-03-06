package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Customer

interface CustomerStrategy  {
    fun action(): Action
    fun can(customer: Customer)
}