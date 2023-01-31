package br.com.acalv3.resources.repository.impl

import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.repository.CustomerRepository
import br.com.acalv3.resources.model.business.toCustomer
import br.com.acalv3.resources.model.business.toCustomerModel
import br.com.acalv3.resources.model.business.toCustomerPage
import br.com.acalv3.resources.repository.CustomerRepositoryJpa
import br.com.acalv3.resources.repository.specification.CustomerSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepositoryImpl(
    private val customerRepositoryJpa: CustomerRepositoryJpa,
) : CustomerRepository{
    override fun getById(id: UUID): Customer =
        customerRepositoryJpa.findByIdOrNull(id)?.toCustomer() ?: throw NotFoundException()

    override fun save(customer: Customer): Customer =
        customerRepositoryJpa.save(customer.toCustomerModel()).toCustomer()

    override fun update(customer: Customer): Customer =
        customerRepositoryJpa.save(customer.toCustomerModel()).toCustomer()

    override fun findByName(name: String): Customer =
        customerRepositoryJpa.findByName(name).toCustomer()

    override fun paginate(request: CustomerPageRequest): Page<Customer> = run {
        val selectSortedField = request.sortedField ?: "id"
        val selectDirection = when(request.direction){
            ASC.name -> { ASC }
            DESC.name -> { DESC}
            else -> ASC
        }

        val selectPageNumber: Int = request.page?.let { if(it<0) 0 else it } ?: 0
        val selectPageSize: Int  = request.pageSize?.let { if(it<0) 1 else it } ?: 0

        val spec  = CustomerSpecification(request).getSpecification()
        val page = PageRequest.of(selectPageNumber,selectPageSize)
            .withSort(Sort.by(selectDirection, selectSortedField))

        customerRepositoryJpa.findAll(spec ,page ).toCustomerPage()
    }


}