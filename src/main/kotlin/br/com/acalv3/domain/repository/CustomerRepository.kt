package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Customer
import br.com.acalv3.domain.model.page.CustomerPage

interface CustomerRepository: AbstractRepository<Customer, CustomerPage>{
    fun findByName(name: String): Customer
    fun findByDocument(document: String): Customer?
    override fun count(): Long
}