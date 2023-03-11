package br.com.acalv3.domain.repository

import br.com.acalv3.domain.model.Address
import br.com.acalv3.domain.model.page.AddressPage

interface AddressRepository:  AbstractRepository<Address, AddressPage>{
    fun findByName(name: String): Address?
}