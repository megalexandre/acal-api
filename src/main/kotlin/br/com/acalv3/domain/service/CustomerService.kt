package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Customer

interface CustomerService {

    fun save(customer: Customer): Customer
    fun findByName(name: String): Customer
}