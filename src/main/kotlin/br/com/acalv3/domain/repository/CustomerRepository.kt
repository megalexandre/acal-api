package br.com.acalv3.domain.repository

import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import org.springframework.data.domain.Page
import java.util.*

interface CustomerRepository {
    fun getById(id: UUID): Customer
    fun save(customer: Customer): Customer
    fun update(customer: Customer): Customer
    fun findByName(name: String): Customer
    fun paginate(customerPageRequest: CustomerPageRequest): Page<Customer>

}