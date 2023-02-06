package br.com.acalv3.domain.service

import br.com.acalv3.application.comunicate.model.request.customer.CustomerPageRequest
import br.com.acalv3.domain.model.Customer
import org.springframework.data.domain.Page

interface CustomerService {
    fun getById(id: String): Customer
    fun save(customer: Customer): Customer
    fun update(customer: Customer): Customer
    fun paginate(customerPageRequest: CustomerPageRequest): Page<Customer>
    fun findByName(name: String): Customer
}