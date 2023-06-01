package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.service.LinkService
import org.springframework.stereotype.Service

@Service
class DeleteCustomerStrategy(
    val linkService: LinkService
): CustomerStrategy<Customer> {

    override fun action() = DELETE

    override fun can(model: Customer) {
        linkService.findByCustomerId(model.id)?.let {
            throw RuntimeException(String.format(ERROR_DELETE, it.addressName)
            )
        }
    }

    companion object{
        private const val ERROR_DELETE = "O usúario não pode ser apagado porque está associado as ligações: %s"
    }
}