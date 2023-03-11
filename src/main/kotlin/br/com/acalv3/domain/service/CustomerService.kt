package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.domain.service.strategies.customer.CustomerStrategy

abstract class CustomerService: AbstractService<Customer, CustomerPage>() {
    abstract override fun strategies(): List<CustomerStrategy<Customer>>
    abstract override fun repository(): CustomerRepository

    abstract fun findByName(name: String): Customer
}
