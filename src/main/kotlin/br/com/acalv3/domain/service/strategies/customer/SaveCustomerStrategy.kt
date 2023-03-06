package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class SaveCustomerStrategy(
    val repository: CustomerRepository,
): CustomerStrategy {

    override fun action() = SAVE

    override fun can(customer: Customer) {
        repository.findByDocument(customer.document)?.let {
            throw RuntimeException(
                "o documento ${customer.document} já está cadastrado para o usuário: ${customer.name}")
        }
    }
}