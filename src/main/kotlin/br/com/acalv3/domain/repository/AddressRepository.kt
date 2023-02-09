package br.com.acalv3.domain.repository

import br.com.acalv3.application.comunicate.model.request.address.AddressPageRequest
import br.com.acalv3.domain.model.Address
import org.springframework.data.domain.Page

interface AddressRepository {
    fun getById(id: String): Address
    fun save(address: Address): Address
    fun update(customer: Address): Address
    fun findByName(name: String): Address
    fun paginate(pageRequest: AddressPageRequest): Page<Address>

}