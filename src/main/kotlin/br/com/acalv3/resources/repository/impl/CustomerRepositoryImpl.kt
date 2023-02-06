package br.com.acalv3.resources.repository.impl

import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.model.business.toCustomerEntity
import br.com.acalv3.resources.model.business.toCustomerPage
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.interfaces.CustomerRepositoryJpa
import br.com.acalv3.resources.repository.specification.CustomerSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepositoryImpl(
    private val repository: CustomerRepositoryJpa,
) : CustomerRepository, DefaultRepository<Customer> {

    override fun getById(id: String): Customer =
        repository.findByIdOrNull(UUID.fromString(id))?.toCustomer() ?: throw NotFoundException()

    override fun save(customer: Customer): Customer =
        repository.save(customer.toCustomerEntity()).toCustomer()

    override fun update(customer: Customer): Customer =
        repository.save(customer.toCustomerEntity()).toCustomer()

    override fun findByName(name: String): Customer =
        repository.findByName(name).toCustomer()

    override fun paginate(request: CustomerPageRequest): Page<Customer> =
        repository.findAll(
            CustomerSpecification(request).getSpecification(),
            super.pageable(request)
        ).toCustomerPage()

}
