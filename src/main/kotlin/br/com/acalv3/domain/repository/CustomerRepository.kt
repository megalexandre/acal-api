package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Customer

interface CustomerRepository {

    fun save(customer: Customer): Customer
    fun findByName(name: String): Customer

}