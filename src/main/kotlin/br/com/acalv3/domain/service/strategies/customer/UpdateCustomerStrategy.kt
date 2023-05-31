package br.com.acalv3.domain.service.strategies.customer

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
                throw RuntimeException("o documento ${it.document} já está cadastrado para o usuário: ${it.name}")
            }
        }
    }

}