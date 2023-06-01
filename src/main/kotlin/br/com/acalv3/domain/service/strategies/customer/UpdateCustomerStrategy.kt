package br.com.acalv3.domain.service.strategies.customer

import br.com.acalv3.commons.formatDocument
import br.com.acalv3.domain.enumeration.Action.UPDATE
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class UpdateCustomerStrategy(
    val repository: CustomerRepository,
): CustomerStrategy<Customer>  {

    override fun action() = UPDATE

    override fun can(model: Customer) {
        repository.findByDocument(model.document)?.let {
            if(it.id != model.id){
                throw RuntimeException(String.format(ERROR_DUPLICATED_CUSTOMER, it.document.formatDocument(),it.name))
            }
        } ?: throw RuntimeException(String.format(ERROR_NOT_FOUND_CUSTOMER, model.document.formatDocument()))
    }

    companion object {
        private const val ERROR_DUPLICATED_CUSTOMER = "O documento %s já está cadastrado para o usuário: %s"
        const val ERROR_NOT_FOUND_CUSTOMER = "O documento %s não está cadastrado"
    }
}