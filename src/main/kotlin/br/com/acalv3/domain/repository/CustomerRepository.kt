package br.com.acalv3.domain.repository

import br.com.acalv3.application.comunicate.model.request.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import org.springframework.data.domain.Page

interface CustomerRepository {

    fun save(customer: Customer): Customer
    fun findByName(name: String): Customer
    fun paginate(customerPageRequest: CustomerPageRequest): Page<Customer>

}