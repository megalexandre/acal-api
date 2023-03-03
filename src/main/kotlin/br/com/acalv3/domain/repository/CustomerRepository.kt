package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage
import org.springframework.data.domain.Page

interface CustomerRepository {
    fun getById(id: String): Customer
    fun delete(id: String)
    fun save(customer: Customer): Customer
    fun update(customer: Customer): Customer
    fun findByName(name: String): Customer
    fun findByDocument(document: String): Customer?
    fun paginate(customerPage: CustomerPage): Page<Customer>
    fun count(): Long
}