package br.com.acalv3.resources.repository.impl

import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.model.business.toCustomerModel
import br.com.acalv3.resources.model.business.toCustomerPage
import br.com.acalv3.resources.repository.interfaces.CustomerRepositoryJpa
import br.com.acalv3.resources.repository.DefaultRepository
import br.com.acalv3.resources.repository.specification.CustomerSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepositoryImpl(
    private val customerRepositoryJpa: CustomerRepositoryJpa,
) : CustomerRepository, DefaultRepository {
    override fun getById(id: UUID): Customer =
        customerRepositoryJpa.findByIdOrNull(id)?.toCustomer() ?: throw NotFoundException()

    override fun save(customer: Customer): Customer =
        customerRepositoryJpa.save(customer.toCustomerModel()).toCustomer()

    override fun update(customer: Customer): Customer =
        customerRepositoryJpa.save(customer.toCustomerModel()).toCustomer()

    override fun findByName(name: String): Customer =
        customerRepositoryJpa.findByName(name).toCustomer()

    override fun paginate(request: CustomerPageRequest): Page<Customer> =
        customerRepositoryJpa.findAll(
            CustomerSpecification(request).getSpecification(),
            super.paginate(request)
        ).toCustomerPage()
    }
