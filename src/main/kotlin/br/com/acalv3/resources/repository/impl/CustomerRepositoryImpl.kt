package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.model.business.toCustomerModel
import br.com.acalv3.resources.repository.CustomerRepositoryJpa
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(
    private val customerRepositoryJpa: CustomerRepositoryJpa
) : CustomerRepository{

    override fun save(customer: Customer): Customer =
        customerRepositoryJpa.save(customer.toCustomerModel()).toCustomer()

    override fun findByName(name: String): Customer =
        customerRepositoryJpa.findByName(name).toCustomer()
}