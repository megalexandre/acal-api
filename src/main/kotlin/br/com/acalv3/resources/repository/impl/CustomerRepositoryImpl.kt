package br.com.acalv3.resources.repository.impl

import br.com.acalv3.application.comunicate.model.request.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.model.business.toCustomerModel
import br.com.acalv3.resources.model.business.toCustomerPage
import br.com.acalv3.resources.repository.CustomerRepositoryJpa
import br.com.acalv3.resources.repository.specification.CustomerSpecification
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(
    private val customerRepositoryJpa: CustomerRepositoryJpa,
) : CustomerRepository{

    override fun save(customer: Customer): Customer =
        customerRepositoryJpa.save(customer.toCustomerModel()).toCustomer()

    override fun findByName(name: String): Customer =
        customerRepositoryJpa.findByName(name).toCustomer()

    override fun paginate(customerPageRequest: CustomerPageRequest): Page<Customer> = run {
        val spec  = CustomerSpecification(customerPageRequest).getSpecification()
        val page = PageRequest.of(0, 10).withSort(Sort.by(Sort.Direction.ASC, "id"))

        customerRepositoryJpa.findAll(spec ,page ).toCustomerPage()
    }


}