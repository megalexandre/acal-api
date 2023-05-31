package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action.DELETE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.LinkService
import org.springframework.stereotype.Service

@Service
class DeleteCustomerStrategy(
    val repository: CustomerRepository,
    val linkService: LinkService
): CustomerStrategy<Customer> {

    override fun action() = DELETE

    override fun can(model: Customer) {
        linkService.findByCustomerId(model.id)?.let {
            throw RuntimeException("O usúario não pode ser apagado porque está associado as ligações: " + it.place?.address?.name
            )
        }
    }

}