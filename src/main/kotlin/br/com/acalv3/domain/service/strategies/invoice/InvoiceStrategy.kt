package br.com.acalv3.domain.service.strategies.invoice

import br.com.acalv3.domain.enumeration.Action
import br.com.acalv3.domain.model.Invoice
import br.com.acalv3.domain.service.strategies.AbstractStrategy

interface InvoiceStrategy<Q: Any> : AbstractStrategy<Invoice> {
    override fun action(): Action
    override fun can(model: Invoice)
}