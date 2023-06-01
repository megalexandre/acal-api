package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.commons.formatDocument
import br.com.acalv3.domain.enumeration.Action.SAVE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class SaveCustomerStrategy(
    val repository: CustomerRepository,
): CustomerStrategy<Customer>  {

    override fun action() = SAVE

    override fun can(model: Customer) {
        repository.findByDocument(model.document)?.let {
            throw RuntimeException(String.format(ERROR_DUPLICATED,it.document.formatDocument(), it.name ))
        }
    }

    companion object {
        private const val ERROR_DUPLICATED = "o documento %s já está cadastrado para o usuário: %s"
    }
}