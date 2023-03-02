package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import org.springframework.data.domain.Page

interface CustomerService {
    fun getById(id: String): Customer
    fun delete(id: String)
    fun save(customer: Customer): Customer
    fun update(customer: Customer): Customer
    fun paginate(customerPageRequest: CustomerPage): Page<Customer>
    fun findByName(name: String): Customer
}