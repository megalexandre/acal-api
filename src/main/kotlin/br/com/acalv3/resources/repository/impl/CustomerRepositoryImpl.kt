package br.com.acalv3.resources.repository.impl

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.model.business.toCustomerEntity
import br.com.acalv3.resources.model.business.toCustomerPage
import br.com.acalv3.resources.repository.interfaces.CustomerRepositoryJpa
import br.com.acalv3.resources.repository.specification.CustomerSpecification
import java.util.UUID
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl(
    private val repository: CustomerRepositoryJpa,
) : CustomerRepository {

    override fun getById(id: String): Customer =
        repository.findByIdOrNull(UUID.fromString(id))?.toCustomer() ?: throw NotFoundException()

    override fun getAll(): List<Customer> = repository.findAll().toCustomer()

    override fun delete(id: String) = repository.deleteById(UUID.fromString(id))

    override fun save(customer: Customer): Customer =
        repository.save(customer.toCustomerEntity()).toCustomer()

    override fun findByName(name: String): Customer =
        repository.findByName(name).toCustomer()

    override fun findByDocument(document: String): Customer? = repository.findByDocument(document)?.toCustomer()

    override fun paginate(page: CustomerPage): Page<Customer> =
        repository.findAll(
            CustomerSpecification(page).getSpecification(),
            super.pageable(page)
        ).toCustomerPage()

    override fun count(): Long = repository.count()

}
